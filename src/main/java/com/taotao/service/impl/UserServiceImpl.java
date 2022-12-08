package com.taotao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.Result;
import com.taotao.dto.UserLoginFormDTO;
import com.taotao.entity.User;
import com.taotao.mapper.UserMapper;
import com.taotao.service.UserService;
import com.taotao.util.RegexUtils;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.taotao.util.RedisConstants.*;
import static com.taotao.util.SystemConstants.*;


/**
* @author YuLong
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-21 14:08:09
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    /**
     * 发送短信验证码
     * @param phone 电话号码
     * @return  Result
     */
    @Override
    public Result sendCode(String phone) {
        // 1.校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        }
        // 3.符合，生成验证码
        String authCode = RandomUtil.randomNumbers(AUTH_CODE_LENGTH);
        // 4.保存验证码到 redis
        String codeKey = LOGIN_CODE_KEY + phone;
        long codeTime = LOGIN_CODE_TTL + RandomUtil.randomLong(LOGIN_CODE_TTL) / 2;
        stringRedisTemplate.opsForValue().set(codeKey, JSONUtil.toJsonStr(authCode), codeTime, TimeUnit.MINUTES);
        log.info("用户点击发送验证码时获取到的手机号码： {}", phone);
        // 5.发送验证码
        log.debug("发送短信验证码成功，验证码：{}", authCode);
        // 返回 success
        return Result.success();
    }

    /**
     * 用户登录功能
     * @param userLoginFormDTO 用户登录信息DTO
     * @return  Result
     */
    @Override
    public Result login(UserLoginFormDTO userLoginFormDTO) {
        // 1.校验手机号
        String phone = userLoginFormDTO.getPhone();
        String authCode = userLoginFormDTO.getCode();
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机格式不正确");
        }
        // 2.从redis中获取短信验证码并校验
        String codeKey = LOGIN_CODE_KEY + phone;
        String cacheCode = stringRedisTemplate.opsForValue().get(codeKey);
        if (cacheCode == null || !cacheCode.equals(authCode)) {
            // 2.1.不一致，报错
            return Result.fail("验证码错误");
        }
        // 2.2.一致，根据手机号查询用户 select * from user where phone = ?
        User user = query().eq(AUTH_PHONE, phone).one();
        // 3.判断该用户是否存在
        if (user == null) {
            // 4.用户不存在，创建新用户并保存到数据库
            log.info("用户不存在，创建新用户中。。。");
            user = createWithPhone(phone);
        }
        // 5.保存用户信息到 redis
        // 5.1.随机生成 token，作为登录令牌
        String token = UUID.randomUUID().toString(true);
        // 5.2.将 User对象转为 HashMap存储
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        String userJson = JSONUtil.toJsonStr(userVO);
        // 5.3.存储
        String userTokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForValue().set(userTokenKey, userJson);
        // 5.4.设置 token有效期
        long userTokenTime = LOGIN_USER_TTL + RandomUtil.randomLong(LOGIN_USER_TTL) / 2;
        stringRedisTemplate.expire(userTokenKey, userTokenTime, TimeUnit.SECONDS);
        log.info("用户登录成功");
        // 6.返回 token
        return Result.success(token);
    }

    /**
     * 用户重置手机号码
     * @param oldPhone 原手机号
     * @param phone 新手机号
     * @param code 验证码
     * @return  Result
     */
    @Override
    public Result resetPhone(String oldPhone, String phone, String code) {
        // 校验手机号
        if (RegexUtils.isPhoneInvalid(oldPhone)) {
            return Result.fail("手机格式不正确");
        }
        // 从redis中获取短信验证码并校验
        String codeKey = LOGIN_CODE_KEY + oldPhone;
        String cacheCode = stringRedisTemplate.opsForValue().get(codeKey);
        if (cacheCode == null || !cacheCode.equals(code)) {
            // 不一致，报错
            return Result.fail("验证码错误");
        }
        // 一致，根据手机号查询用户，验证该手机是否存在
        String existUserPhone = userMapper.selectPhoneOfUser(oldPhone);
        if (existUserPhone == null) {
            // 不存在，返回 fail
            return Result.fail("原手机号不存在");
        }
        // 存在则继续查询在数据库是否存在与新手机号重复的手机号码
        String repeatPhone = userMapper.selectPhoneOfUser(phone);
        // 若重复则返回 fail
        if (repeatPhone != null) {
            return Result.fail("该手机号已注册！");
        }
        return Result.success();
    }

    /**
     * 查询商家热力榜的单个商家所属个人信息
     * @param id 商家id
     * @return  Result
     */
    @Override
    public User querySingleHotRankOfUserInfo(Long id) {
        return userMapper.selectSingleHotRandOfUserInfo(id);
    }

    /**
     * 查询商家热力榜的所有商家所属个人信息
     * @param ids id列表
     * @return  Result
     */
    @Override
    public List<User> queryListHotRankOfUserInfo(List<Long> ids) {
        List<String> str = new ArrayList<>();
        ids.forEach( (item) -> str.add(String.valueOf(item)));
        return userMapper.selectListHotRandOfUserInfo(str);
    }

    /**
     * 更新用户个人信息
     * @param user 用户信息
     */
    @Override
    public void modifyUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    /**
     * 用户实名认证
     * @param user 用户实名信息
     */
    @Override
    public void modifyUserRealName(User user) {
        userMapper.updateUserRealName(user);
    }

    /**
     * 根据 userId查询数据库中密码
     * @param userId 用户账号
     * @return 数据库中的密码
     */
    @Override
    public String queryPasswordOfDatabase(Long userId) {
        return userMapper.selectPasswordOfDatabase(userId);
    }

    private User createWithPhone(String phone) {
        // 用户昵称 = 固定前缀 + 随机后缀字符串
        String nickName = USER_NICK_NAME_PREFIX + RandomUtil.randomString(USER_NICK_AFTER_DIGIT);
        // 1.创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(nickName);
        // 2.保存用户
        save(user);
        log.info("创建新用户成功，新用户昵称为：{}", nickName);
        return user;
    }
}




