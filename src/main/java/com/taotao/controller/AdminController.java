package com.taotao.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.dto.AdminDTO;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.PageData;
import com.taotao.dto.Result;
import com.taotao.entity.Admin;
import com.taotao.entity.Merchant;
import com.taotao.entity.User;
import com.taotao.service.AdminService;
import com.taotao.util.AdminHolder;
import com.taotao.vo.MerchantVO;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

import static com.taotao.util.SystemConstants.*;

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
    public Result<String> sendCode(@PathVariable("phone") String phone) {
        return adminService.sendCodeOfTel(phone);
    }

    /**
     * 管理员登录
     * @param adminLoginFormDTO 管理员登录DTO
     * @return 通用返回结果
     */
    @PostMapping("/login")
    public Result<String> adminLogin(@RequestBody AdminLoginFormDTO adminLoginFormDTO) {
        return adminService.loginProcessingFlow(adminLoginFormDTO);
    }

    /**
     * 管理员注册
     * @param adminLoginFormDTO 管理员登录DTO
     * @return 通用返回结果
     */
    @PostMapping("/register")
    public Result<String> adminRegister(@RequestBody AdminLoginFormDTO adminLoginFormDTO) {
        return adminService.registerProcess(adminLoginFormDTO);
    }

    /**
     * 管理员退出登录
     * @return 通用返回结果
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        AdminHolder.removeAdmin();
        return Result.success("退出登录成功");
    }

    /**
     * 获取当前登录的管理员信息
     * @return 通用返回结果
     */
    @GetMapping("/me")
    public Result<Admin> me() {
        AdminDTO adminDTO = AdminHolder.getAdmin();
        return Result.success(adminDTO);
    }

    /**
     * 查看商家（可按关键字搜索）
     * @param jsonObject json格式 分页信息
     * @return 商家分页
     */
    @PostMapping("/page/merchant")
    public Result<Page<Merchant>> viewMerchantPage(@RequestBody JSONObject jsonObject) {
        log.info("管理员查看商家中。。。");
        PageData pageData = JSONUtil.toBean(jsonObject, PageData.class);
        return Result.success(adminService.viewMerchantOfAdminWithTransmitData(pageData));
    }

    /**
     * 查看用户（可按关键字搜索）
     * @param jsonObject json格式 分页信息
     * @return 用户分页
     */
    @PostMapping("/page/user")
    public Result<Page<User>> viewUserPage(@RequestBody JSONObject jsonObject) {
        log.info("管理员查看用户中。。。");
        PageData pageData = JSONUtil.toBean(jsonObject, PageData.class);
        return Result.success(adminService.viewUserOfAdminWithTransmitData(pageData));
    }

    /**
     * 超级管理员查看管理员（可按关键字搜索）
     * @param jsonObject json格式 分页信息
     * @return 管理员分页
     */
    @PostMapping("/page/admin")
    public Result<Page<Admin>> viewAdminPage(@RequestBody JSONObject jsonObject) {
        log.info("超级管理员查看管理员中。。。");
        PageData pageData = BeanUtil.copyProperties(jsonObject, PageData.class);
        Integer isRoot = BeanUtil.copyProperties(jsonObject, AdminDTO.class).getIsRoot();
        if (!Objects.equals(isRoot, IS_ROOT)) {
            return Result.fail("您不是尊贵的超级管理员，无法查看其它管理员");
        }
        return Result.success(adminService.viewAdmin(pageData));
    }

    /**
     * 修改商家状态（启用禁用）
     * @param merchantVO 商家状态信息
     * @return Result
     */
    @PutMapping("/status/merchant")
    public Result<String> changeMerchantStatus(@RequestBody MerchantVO merchantVO) {
        log.info("修改商家状态中。。。");
        Integer status = merchantVO.getStatus();
        Result<String> stringResult = estimateStatus(status);
        if (stringResult.getErrorMsg() != null) {
            return stringResult;
        }
        adminService.modifyMerchantStatusOfAdminWithTransmitData(merchantVO);
        return stringResult;
    }

    /**
     * 修改用户状态（启用禁用）
     * @param userVO 用户状态信息
     * @return Result
     */
    @PutMapping("/status/user")
    public Result<String> changeUserStatus(@RequestBody UserVO userVO) {
        log.info("修改用户状态中。。。");
        Integer status = userVO.getStatus();
        Result<String> stringResult = estimateStatus(status);
        if (stringResult.getErrorMsg() != null) {
            return stringResult;
        }
        adminService.modifyUserStatusOfAdminWithTransmitData(userVO);
        return stringResult;
    }

    /**
     * 超级管理员修改管理员状态（启用禁用）
     * @param adminDTO 管理员状态信息
     * @return Result
     */
    @PutMapping("/status/admin")
    public Result<String> changeUserStatus(@RequestBody AdminDTO adminDTO) {
        log.info("超级管理员修改管理员状态中。。。");
        Integer status = adminDTO.getStatus();
        Integer isRoot = adminDTO.getIsRoot();
        Result<String> stringResult = estimateStatus(status);
        if (stringResult.getErrorMsg() != null) {
            return stringResult;
        }
        if (Objects.equals(isRoot, TRUE)) {
            log.info("不能禁用超级管理员");
            return Result.fail("您不能禁用尊贵的超级管理员！");
        }
        adminService.modifyAdminStatus(adminDTO);
        return stringResult;
    }

    private Result<String> estimateStatus(Integer status) {
        String statusMessage;
        if (!Objects.equals(status, FALSE) && !status.equals(TRUE)) {
            return Result.fail("传入状态参数值有误，修改状态失败！");
        }
        statusMessage = Objects.equals(status, FALSE) ? "禁用成功！" : "启用成功！";
        return Result.success(statusMessage);
    }
}
