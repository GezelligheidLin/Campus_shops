package com.taotao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.Result;
import com.taotao.entity.GoodsType;

/**
* @author YuLong
* @description 针对表【goods_type(商品类型表)】的数据库操作Service
* @createDate 2022-11-21 13:58:22
*/
public interface GoodsTypeService extends IService<GoodsType> {

    /**
     * 查询全部分类图标
     * @return 分类图标 list
     */
    Result queryAllIcon();

    /**
     * 查询分类淘品
     * @param type 分类
     * @return 分类淘品 list
     */
    Result queryClassifyGoods(String type);
}
