package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.Result;
import com.taotao.entity.Commodity;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Service
* @createDate 2022-11-21 14:07:27
*/
public interface CommodityService extends IService<Commodity> {

    /**
     * 首页轮播图查询
     * @return
     */
    Result queryCarousel();

    /**
     * 首页甄选推荐查询
     * @return
     */
    Result queryRecommend();

    /**
     * 首页专区选购查询
     * @return
     */
    Result queryArea();
    /**
     * 首页猜你喜欢查询
     * @return
     */
    Result queryLike();

}
