package com.taotao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-11-21 14:08:09
* @Entity taotao.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




