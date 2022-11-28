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
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    /**
     * 发送短信验证码
     * @param phone
     * @param session
     * @return
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        return adminService.sendCodeOfTel(phone, session);
    }

    /**
     * 管理员登录
     * @param adminLoginFormDTO
     * @return
     */
    @PostMapping("/login")
    public Result adminLogin(@RequestBody AdminLoginFormDTO adminLoginFormDTO, HttpSession session) {
        return adminService.loginProcessingFlow(adminLoginFormDTO, session);
    }

    /**
     * 管理员注册
     * @param adminLoginFormDTO
     * @return
     */
    @PostMapping("/register")
    public Result adminRegister(@RequestBody AdminLoginFormDTO adminLoginFormDTO) {
        return adminService.registerProcess(adminLoginFormDTO);
    }

    /**
     * 管理员退出登录
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        AdminHolder.removeAdmin();
        return Result.success();
    }

    @GetMapping("/me")
    public Result me() {
        // 获取当前登录的管理员信息并返回
        AdminDTO adminDTO = AdminHolder.getAdmin();
        return Result.success(adminDTO);
    }


}
