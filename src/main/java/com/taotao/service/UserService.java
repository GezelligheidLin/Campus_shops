package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.Result;
import com.taotao.dto.UserLoginFormDTO;
import com.taotao.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @author YuLong
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-21 14:08:09
*/
public interface UserService extends IService<User> {

    /**
     * 发送短信验证码
     * @param phone 电话号码
     * @return  Result
     */
    Result sendCode(String phone);

    /**
     * 用户登录功能
     * @param userLoginFormDTO 用户登录信息DTO
     * @param session 会话控制
     * @return  Result
     */
    Result login(UserLoginFormDTO userLoginFormDTO, HttpSession session);

    /**
     * 查询商家热力榜的商家所属个人信息
     * @param ids id列表
     * @return  Resul
     */
    List<User> queryHotRankOfUserInfo(List<Long> ids);
}
