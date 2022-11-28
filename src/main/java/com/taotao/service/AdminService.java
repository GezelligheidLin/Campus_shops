package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.Result;
import com.taotao.entity.Admin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
* @author YuLong
* @description 针对表【admin(管理员表)】的数据库操作Service
* @createDate 2022-11-21 14:06:42
*/
public interface AdminService extends IService<Admin> {
    /**
     * 发送短信验证码
     * @param phone
     * @param session
     * @return
     */
    Result sendCodeOfTel(String phone, HttpSession session);

    /**
     * 管理员登录处理流程
     * @param adminLoginFormDTO
     * @param session
     * @return
     */
    Result loginProcessingFlow(AdminLoginFormDTO adminLoginFormDTO, HttpSession session);

    /**
     * 管理员注册流程
     * @param adminLoginFormDTO
     * @return
     */
    Result registerProcess(AdminLoginFormDTO adminLoginFormDTO);


}
