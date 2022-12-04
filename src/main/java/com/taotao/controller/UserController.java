package com.taotao.controller;

import cn.hutool.core.bean.BeanUtil;
import com.taotao.dto.Result;
import com.taotao.dto.UserInfoDTO;
import com.taotao.dto.UserLoginFormDTO;
import com.taotao.entity.User;
import com.taotao.service.UserService;
import com.taotao.util.IdCardVerification;
import com.taotao.util.RegexUtils;
import com.taotao.util.UserHolder;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @return success
     */
    @GetMapping("/code/{phone}")
    public Result sendCode(@PathVariable("phone") String phone) {
        log.info("phone = {}", phone);
        return userService.sendCode(phone);
    }

    /**
     * 用户登录功能
     * @param userLoginFormDTO 用户登录DTO
     * @param session 会和控制
     * @return 用户DTO信息 和 session
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody UserLoginFormDTO userLoginFormDTO, HttpSession session) {
        log.info("session = {}", session);
        log.info("user = {}", userLoginFormDTO);
        return userService.login(userLoginFormDTO, session);
    }

    /**
     * 用户登出
     * @return success
     */
    @PostMapping("/logout")
    public Result userLogout() {
        UserHolder.removeUser();
        return Result.success();
    }

    @GetMapping("/me")
    public Result me() {
        // 获取当前登录的用户并返回
        UserVO userVO = UserHolder.getUser();
        log.info("userVO = {}", userVO);
        return Result.success(userVO);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long userId) {
        // 查询详情
        User info = userService.getById(userId);
        if (info == null) {
            // 没有详情，应该是第一次查看详情
            return Result.success();
        }
        // 仅展示必要信息
        UserInfoDTO userinfoDTO = BeanUtil.copyProperties(info, UserInfoDTO.class);
        // 返回
        return Result.success(userinfoDTO);
    }

    private static final String ID_VERIFY_MESSAGE = "该身份证有效！";

    @PutMapping("/update")
    public Result update(@RequestBody UserInfoDTO userInfoDTO) {
        log.info("用户个人信息修改。。。");
        String cardId = userInfoDTO.getCardId();
        String phone = userInfoDTO.getPhone();
        String idMessage = IdCardVerification.idCardValidate(cardId);
        log.info("idMessage = {}", idMessage);
        if (!ID_VERIFY_MESSAGE.equals(idMessage)) {
            return Result.fail(idMessage);
        }
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误");
        }
        User user = BeanUtil.copyProperties(userInfoDTO, User.class);
        userService.updateUserInfo(user);
        return Result.success("用户信息修改成功");
    }

}
