package com.taotao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author YuLong
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-11-21 14:08:09
* @Entity taotao.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 单次查找商家热力榜的商家所属个人信息
     * @param id 商家id
     * @return 商家所属个人信息
     */
    User selectSingleHotRandOfUserInfo(Long id);


    /**
     * 单次查找商家热力榜的商家所属个人信息
     * @param ids id list
     * @return 商家所属个人信息
     */
    List<User> selectListHotRandOfUserInfo(List<String> ids);

    /**
     * 更新用户个人信息
     * @param user 用户信息
     */
    void updateUser(User user);
}




