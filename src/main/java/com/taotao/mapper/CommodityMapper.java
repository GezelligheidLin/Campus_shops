package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:27
* @Entity taotao.entity.Commodity
*/
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    /**
     * 查找轮播图
     * @param carouselSize 轮播图数量
     * @return 返回轮播图 list
     */
    List<Commodity> selectCarousel(int carouselSize);

    /**
     * 查找甄选推荐
     * @param recommendSize 推荐淘品数量
     * @return 返回推荐淘品 list
     */
    List<Commodity> selectRecommend(int recommendSize);

    /**
     * 查找专区选购
     * @param areaSize 专区数量
     * @return 返回专区 list
     */
    List<Commodity> selectArea(int areaSize);

    /**
     * 查找猜你喜欢
     * @param likeSize 喜欢淘品数量
     * @return 返回喜欢淘品 list
     */
    List<Commodity> selectLike(int likeSize);
}




