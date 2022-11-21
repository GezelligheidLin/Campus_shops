package com.taotao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.User;
import com.taotao.mapper.UserMapper;
import com.taotao.service.UserService;
import org.springframework.stereotype.Service;


/**
* @author YuLong
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-21 14:08:09
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




