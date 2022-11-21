package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【admin(管理员表)】的数据库操作Mapper
* @createDate 2022-11-21 14:06:42
* @Entity taotao.entity.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}




