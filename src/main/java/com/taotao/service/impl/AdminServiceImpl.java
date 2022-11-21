package com.taotao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Admin;
import com.taotao.mapper.AdminMapper;
import com.taotao.service.AdminService;
import org.springframework.stereotype.Service;

/**
* @author YuLong
* @description 针对表【admin(管理员表)】的数据库操作Service实现
* @createDate 2022-11-21 14:06:42
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}




