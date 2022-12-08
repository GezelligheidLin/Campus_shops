package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.Result;
import com.taotao.entity.Admin;


/**
* @author YuLong
* @description 针对表【admin(管理员表)】的数据库操作Service
* @createDate 2022-11-21 14:06:42
*/
public interface AdminService extends IService<Admin> {
    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @return Result
     */
    Result sendCodeOfTel(String phone);

    /**
     * 管理员登录处理流程
     * @param adminLoginFormDTO 管理员登录DTO
     * @return Result
     */
    Result loginProcessingFlow(AdminLoginFormDTO adminLoginFormDTO);

    /**
     * 管理员注册流程
     * @param adminLoginFormDTO 管理员登录DTO
     * @return Result
     */
    Result registerProcess(AdminLoginFormDTO adminLoginFormDTO);


}
