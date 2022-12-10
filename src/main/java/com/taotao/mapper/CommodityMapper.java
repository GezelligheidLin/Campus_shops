package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Commodity;
import com.taotao.vo.CommodityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:27
* @Entity com.taotao.entity.Commodity
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

    /**
     * 查找根据关键字搜索
     * @param key 关键字
     * @return 商品 list
     */
    List<Commodity> selectKeyword(String key);

    /**
     * 根据淘品名查询淘品
     * @param commodityName 淘品名
     * @return 淘品
     */
    Commodity selectNameOfCommodity(String commodityName);

    /**
     * 插入淘品信息
     * @param commodityVO 淘品视图对象
     */
    void insertGoods(CommodityVO commodityVO);
}




