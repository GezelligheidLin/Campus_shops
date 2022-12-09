package com.taotao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.taotao.dto.AdminDTO;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.PageData;
import com.taotao.dto.Result;
import com.taotao.service.AdminService;
import com.taotao.util.AdminHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

import static com.taotao.util.SystemConstants.IS_ROOT;

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
     * @return 通用返回结果
     */
    @GetMapping("/code/{phone}")
    public Result sendCode(@PathVariable("phone") String phone) {
        return adminService.sendCodeOfTel(phone);
    }

    /**
     * 管理员登录
     * @param adminLoginFormDTO 管理员登录DTO
     * @return 通用返回结果
     */
    @PostMapping("/login")
    public Result adminLogin(@RequestBody AdminLoginFormDTO adminLoginFormDTO) {
        return adminService.loginProcessingFlow(adminLoginFormDTO);
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

    /**
     * 查看商家（可按关键字搜索）
     * @param jsonObject json格式 分页信息
     * @return 商家分页
     */
    @PostMapping("/page/merchant")
    public Result viewMerchantPage(@RequestBody JSONObject jsonObject) {
        PageData pageData = JSONUtil.toBean(jsonObject, PageData.class);
        log.info("pageData = {}", pageData);
        return Result.success(adminService.viewMerchantOfAdminWithTransmitData(pageData));
    }

    /**
     * 查看用户（可按关键字搜索）
     * @param jsonObject json格式 分页信息
     * @return 用户分页
     */
    @PostMapping("/page/user")
    public Result viewUserPage(@RequestBody JSONObject jsonObject) {
        PageData pageData = JSONUtil.toBean(jsonObject, PageData.class);
        log.info("pageData = {}", pageData);
        return Result.success(adminService.viewUserOfAdminWithTransmitData(pageData));
    }


    @PostMapping("/page/admin")
    public Result viewAdminPage(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject = {}", jsonObject);
        PageData pageData = BeanUtil.copyProperties(jsonObject, PageData.class);
        Integer isRoot = BeanUtil.copyProperties(jsonObject, AdminDTO.class).getIsRoot();
        if (!Objects.equals(isRoot, IS_ROOT)) {
            return Result.fail("您不是尊贵的超级管理员，无法查看其它管理员");
        }
        return Result.success(adminService.viewAdmin(pageData));
    }


}
