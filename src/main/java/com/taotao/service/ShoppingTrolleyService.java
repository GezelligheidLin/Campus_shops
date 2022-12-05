package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.entity.ShoppingTrolley;

/**
* @author YuLong
* @description 针对表【shopping_trolley(购物车表)】的数据库操作Service
* @createDate 2022-11-21 14:07:58
*/
public interface ShoppingTrolleyService extends IService<ShoppingTrolley> {

    /**
     * 查询购物车
     * @param id 用户账号
     * @return 购物车信息
     */
    ShoppingTrolley queryTrolley(Long id);

    /**
     * 更新购物车
     * @param trolley 购物车
     */
    void modifyTrolley(ShoppingTrolley trolley);
}
