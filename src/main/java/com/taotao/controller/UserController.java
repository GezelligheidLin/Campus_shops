package com.taotao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.taotao.dto.Result;
import com.taotao.dto.UserInfoDTO;
import com.taotao.dto.UserLoginFormDTO;
import com.taotao.entity.User;
import com.taotao.service.UserService;
import com.taotao.util.IdCardVerification;
import com.taotao.util.PasswordEncoder;
import com.taotao.util.UserHolder;
import com.taotao.vo.UserInfoVO;
import com.taotao.vo.UserRealVO;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.taotao.util.RedisConstants.USER_TOKEN_KEY;
import static com.taotao.util.RedisConstants.USER_TOKEN_TTL;
import static com.taotao.util.SystemConstants.*;

/**
 * @author YuLong
 * Date: 2022/11/21 14:09
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号码
     * @return Result
     */
    @GetMapping("/code/{phone}")
    public Result<String> sendCode(@PathVariable("phone") String phone) {
        log.info("phone = {}", phone);
        return userService.sendCode(phone);
    }

    /**
     * 用户登录功能
     * @param userLoginFormDTO 用户登录DTO
     * @return Result
     */
    @PostMapping("/login")
    public Result<String> userLogin(@RequestBody UserLoginFormDTO userLoginFormDTO) {
        log.info("user = {}", userLoginFormDTO);
        return userService.login(userLoginFormDTO);
    }

    /**
     * 用户登出
     * @return Result
     */
    @PostMapping("/logout")
    public Result<String> userLogout() {
        UserHolder.removeUser();
        return Result.success("退出登录成功");
    }

    /**
     * 查看"我的"页面时所展现的个人信息（userId nickName icon）
     * @return 返回个人页信息
     */
    @GetMapping("/me")
    public Result<UserVO> me() {
        // 获取当前登录的用户并返回
        UserVO userVO = UserHolder.getUser();
        log.info("userVO = {}", userVO);
        return Result.success(userVO);
    }

    /**
     * 查看用户常规信息
     * @param userId 用户账号
     * @return 用户常规信息
     */
    @GetMapping("/info/{id}")
    public Result<UserInfoVO> info(@PathVariable("id") Long userId) {
        // 查询详情
        User info = userService.getById(userId);
        if (info == null) {
            // 没有详情，应该是第一次查看详情
            return Result.success();
        }
        // 仅展示必要信息
        UserInfoVO userInfoVO = BeanUtil.copyProperties(info, UserInfoVO.class);
        // 返回用户常规信息
        return Result.success(userInfoVO);
    }

    /**
     * 查看用户实名信息
     * @param userId 用户账号
     * @return 用户实名信息
     */
    @GetMapping("/viewReal/{id}")
    public Result<UserRealVO> viewReal(@PathVariable("id") Long userId) {
        // 先根据 userId查出该用户的所有信息
        User info = userService.getById(userId);
        // 判断该用户是否存在
        if (info == null) {
            // 不存在直接返回 空信息
            return Result.success();
        }
        // 仅返回实名信息
        UserRealVO userRealVO = BeanUtil.copyProperties(info, UserRealVO.class);
        return Result.success(userRealVO);
    }

    /**
     * 修改用户常规信息
     * @param userInfoDTO 用户个人信息DTO
     * @return Result
     */
    @PutMapping("/update")
    public Result<String> updateUserInfo(@RequestBody UserInfoDTO userInfoDTO, HttpServletRequest request) {
        log.info("用户常规信息修改。。。");
        Result<String> res = verifyUserInfo(userInfoDTO);
        // 不为空代表 需要返回修改信息失败结果 or 修改信息项为密码且已通过原密码验证
        if (res != null) {
            // fail
            if (res.getErrorMsg() != null) {
                log.info(res.getErrorMsg());
                return res;
            } else {
                // 将已进行MD5加密的密码set给 userInfoDTO
                String encodePassword = (String) res.getData();
                userInfoDTO.setPassword(encodePassword);
            }
        }
        // 为空则代表信息校验通过，可以到数据库修改相应信息
        User user = BeanUtil.copyProperties(userInfoDTO, User.class);
        if (Objects.equals(userService.modifyUserInfo(user), FALSE)) {
            return Result.fail("修改个人信息失败！");
        }
        String token = request.getHeader(AUTHORIZATION);
        String key = USER_TOKEN_KEY + token;
        String userInfoJson = JSONUtil.toJsonStr(userInfoDTO);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(userInfoJson));
        stringRedisTemplate.expire(key, USER_TOKEN_TTL, TimeUnit.SECONDS);
        return Result.success("用户信息修改成功");
    }

    private static final String ID_VERIFY_MESSAGE = "该身份证有效！";

    /**
     * 用户实名认证（仅可进行一次认证，数据库用 is_real字段来辨识是否已实名，已实名后不可再修改）
     * @param userRealVO 用户实名信息展示类
     * @return Result
     */
    @PutMapping("/realInfo")
    public Result<String> writeRealInfo(@RequestBody UserRealVO userRealVO) {
        log.info("用户正在实名认证中。。。");
        String cardId = userRealVO.getCardId();
        String idMessage = IdCardVerification.idCardValidate(cardId);
        log.info("idMessage = {}", idMessage);
        if (!ID_VERIFY_MESSAGE.equals(idMessage)) {
            return Result.fail(idMessage);
        }
        User user = BeanUtil.copyProperties(userRealVO, User.class);
        userService.modifyUserRealName(user);
        return Result.success("用户实名认证成功！");
    }

    /**
     * 在修改用户信息时 对用户输入的信息进行信息校验
     * @param userInfoDTO 用户个人信息DTO
     * @return Result
     */
    private Result<String> verifyUserInfo(UserInfoDTO userInfoDTO) {
        // userId用于校验密码时查询数据库
        Long userId = userInfoDTO.getUserId();
        // 由于在前端中该模块是单值修改，传参时 userInfoDTO中 以下五项只有一个不为空
        // 因此只需要按序取值，找到不为空的值即为待修改的值，然后进行校验
        String nickName = userInfoDTO.getNickName();
        String oldPhone = userInfoDTO.getOldPhone();
        String rawPassword = userInfoDTO.getRawPassword();
        Date birthday = userInfoDTO.getBirthday();
        String address = userInfoDTO.getAddress();
        // 除手机号和密码外需进行额外校验，其它都只需判断不为空即可
        // (修改手机号码时，需满足 oldPhone, phone, code 三者皆不为空)
        // (修改密码时，需满足 rawPassword 和 password 均不为空)
        // 返回 null代表校验成功 （下同）
        if (nickName != null) {
            if (nickName.length() > (USER_NICK_NAME_PREFIX.length()
                    + USER_NICK_AFTER_DIGIT)) {
                return Result.fail("您输入的昵称过长，请换个昵称~");
            }
            return null;
        } else if (oldPhone != null) {
            // 先在数据库中查找是否存在该用户的手机号
            String phone = userInfoDTO.getPhone();
            String code = userInfoDTO.getCode();
            if (phone == null || code == null) {
                return Result.fail("手机号或验证码错误！");
            }
            Result<String> res = userService.resetPhone(oldPhone, phone, code);
            // 不存在则直接返回 fail
            if (res.getErrorMsg() != null) {
                return res;
            }
            return null;
        } else if (rawPassword != null) {
            // rawPassword 为原密码，encodedPassword为数据库中 MD5加密后的密码，password为新密码
            String encodedPassword = userService.queryPasswordOfDatabase(userId);
            if (encodedPassword == null) {
                return Result.fail("输入密码错误");
            }
            String password = userInfoDTO.getPassword();
            if (!PasswordEncoder.matches(encodedPassword, rawPassword)) {
                log.info("用户输入原密码错误，更改密码失败 encodedPassword = {}, rawPassword = {}",
                        encodedPassword, rawPassword);
                return Result.fail("原密码错误，请重新输入正确密码");
            }
            // 原密码通过数据库验证成功，将新密码用 MD5加密，并返回给 updateUserInfo()接收
            String encodePassword = PasswordEncoder.encode(password);
            return Result.success(encodePassword);
        } else if (birthday != null) {
            return null;
        } else if (address != null) {
            return null;
        }
        // 以上五项都为空则返回
        return null;
    }
}
