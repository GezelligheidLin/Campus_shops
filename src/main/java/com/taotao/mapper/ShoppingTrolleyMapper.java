package com.taotao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.ShoppingTrolley;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【shopping_trolley(购物车表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:58
* @Entity com.taotao.entity.ShoppingTrolley
*/
@Mapper
public interface ShoppingTrolleyMapper extends BaseMapper<ShoppingTrolley> {

    /**
     * 查找购物车信息
     * @param userId 用户账号
     * @return 购物车信息
     */
    ShoppingTrolley selectTrolley(Long userId);

    /**
     * 更新购物车
     * @param trolley 购物车
     */
    void updateTrolley(ShoppingTrolley trolley);
}




