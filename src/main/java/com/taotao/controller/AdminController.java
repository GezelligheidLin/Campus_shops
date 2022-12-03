package com.taotao.controller;

import com.taotao.dto.AdminDTO;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.Result;
import com.taotao.service.AdminService;
import com.taotao.util.AdminHolder;
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
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @param session 会话控制
     * @return 通用返回结果
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        return adminService.sendCodeOfTel(phone, session);
    }

    /**
     * 管理员登录
     * @param adminLoginFormDTO 管理员登录DTO
     * @return 通用返回结果
     */
    @PostMapping("/login")
    public Result adminLogin(@RequestBody AdminLoginFormDTO adminLoginFormDTO, HttpSession session) {
        return adminService.loginProcessingFlow(adminLoginFormDTO, session);
    }

    /**
     * 管理员注册
     * @param adminLoginFormDTO 管理员登录DTO
     * @return 通用返回结果
     */
    @PostMapping("/register")
    public Result adminRegister(@RequestBody AdminLoginFormDTO adminLoginFormDTO) {
        return adminService.registerProcess(adminLoginFormDTO);
    }

    /**
     * 管理员退出登录
     * @return 通用返回结果
     */
    @PostMapping("/logout")
    public Result logout() {
        AdminHolder.removeAdmin();
        return Result.success();
    }

    /**
     * 获取当前登录的管理员信息
     * @return 通用返回结果
     */
    @GetMapping("/me")
    public Result me() {
        AdminDTO adminDTO = AdminHolder.getAdmin();
        return Result.success(adminDTO);
    }


}
