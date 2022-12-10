package com.taotao.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.AdminDTO;
import com.taotao.dto.AdminLoginFormDTO;
import com.taotao.dto.PageData;
import com.taotao.dto.Result;
import com.taotao.entity.Admin;
import com.taotao.entity.Merchant;
import com.taotao.entity.User;
import com.taotao.vo.MerchantVO;
import com.taotao.vo.UserVO;


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
    Result<String> sendCodeOfTel(String phone);

    /**
     * 管理员登录处理流程
     * @param adminLoginFormDTO 管理员登录DTO
     * @return Result
     */
    Result<String> loginProcessingFlow(AdminLoginFormDTO adminLoginFormDTO);

    /**
     * 管理员注册流程
     * @param adminLoginFormDTO 管理员登录DTO
     * @return Result
     */
    Result<String> registerProcess(AdminLoginFormDTO adminLoginFormDTO);

    /**
     * 管理员查询商家（数据传输 adminService -> merchantService）
     * @param pageData 分页信息
     * @return 商家分页
     */
    Page<Merchant> viewMerchantOfAdminWithTransmitData(PageData pageData);

    /**
     * 管理员查询用户（数据传输 adminService -> userService）
     * @param pageData 分页信息
     * @return 用户分页
     */
    Page<User> viewUserOfAdminWithTransmitData(PageData pageData);

    /**
     * 超级管理员查询普通管理员
     * @param pageData 分页信息
     * @return 管理员分页
     */
    Page<Admin> viewAdmin(PageData pageData);

    /**
     * 管理员修改商家状态中间传输方法（数据传输 adminService -> merchantService）
     * @param merchantVO 商家状态信息
     */
    void modifyMerchantStatusOfAdminWithTransmitData(MerchantVO merchantVO);

    /**
     * 管理员修改用户状态中间传输方法（数据传输 adminService -> userService）
     * @param userVO 用户状态信息
     */
    void modifyUserStatusOfAdminWithTransmitData(UserVO userVO);

    /**
     * 超级管理员修改管理员状态
     * @param adminDTO 管理员状态信息
     */
    void modifyAdminStatus(AdminDTO adminDTO);
}
