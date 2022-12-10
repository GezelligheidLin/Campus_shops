package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.Result;
import com.taotao.entity.Commodity;
import com.taotao.vo.CommodityVO;

import java.util.List;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Service
* @createDate 2022-11-21 14:07:27
*/
public interface CommodityService extends IService<Commodity> {

    /**
     * 首页轮播图查询
     * @return 商品 list
     */
    List<Commodity> queryCarousel();

    /**
     * 首页甄选推荐查询
     * @return 商品 list
     */
    List<Commodity> queryRecommend();

    /**
     * 首页专区选购查询
     * @return 商品 list
     */
    List<Commodity> queryArea();

    /**
     * 首页猜你喜欢查询
     * @return 商品 list
     */
    List<Commodity> queryLike();

    /**
     * 根据关键字搜索查询
     * @param key 关键字
     * @return 商品 list
     */
    List<Commodity> queryKeyword(String key);

    /**
     * 保存淘品信息
     * @param commodityVO 淘品视图对象
     * @return Result
     */
    Result<String> saveGoods(CommodityVO commodityVO);
}
