package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.dto.UserDTO;
import com.taotao.dto.UserLoginFormDTO;
import com.taotao.entity.User;
import com.taotao.service.UserService;
import com.taotao.util.UserHolder;
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
        UserDTO userDTO = UserHolder.getUser();
        return Result.success(userDTO);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long userId) {
        // 查询详情
        User info = userService.getById(userId);
        if (info == null) {
            // 没有详情，应该是第一次查看详情
            return Result.success();
        }
        info.setCreateTime(null);
        info.setUpdateTime(null);
        // 返回
        return Result.success(info);
    }

}
