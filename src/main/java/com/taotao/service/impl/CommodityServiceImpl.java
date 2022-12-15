package com.taotao.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.Result;
import com.taotao.entity.Commodity;
import com.taotao.mapper.CommodityMapper;
import com.taotao.service.CommodityService;
import com.taotao.vo.CommodityVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.taotao.util.SystemConstants.LONG_RANDOM_START;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:27
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    private final static int CAROUSEL_SIZE = 4;
    private final static int RECOMMEND_SIZE = 4;
    private final static int AREA_SIZE = 4;
    private final static int LIKE_SIZE = 4;


    /**
     * 首页轮播图查询
     * @return 轮播图 list
     */
    @Override
    public List<Commodity> queryCarousel() {
        return commodityMapper.selectCarousel(CAROUSEL_SIZE);
    }

    /**
     * 首页甄选推荐查询
     * @return 推荐 list
     */
    @Override
    public List<Commodity> queryRecommend() {
        return commodityMapper.selectRecommend(RECOMMEND_SIZE);
    }

    /**
     * 首页专区选购查询
     * @return 专区 list
     */
    @Override
    public List<Commodity> queryArea() {
        return commodityMapper.selectArea(AREA_SIZE);
    }

    /**
     * 首页猜你喜欢查询
     * @return 喜欢 list
     */
    @Override
    public List<Commodity> queryLike() {
        return commodityMapper.selectLike(LIKE_SIZE);
    }

    /**
     * 根据关键字搜索查询
     * @param key 关键字
     * @return 商品 list
     */
    @Override
    public List<Commodity> queryKeyword(String key) {
        return commodityMapper.selectKeyword(key);
    }

    /**
     * 保存淘品信息
     * @param commodityVO 淘品视图对象
     */
    @Override
    public Result<String> saveGoods(CommodityVO commodityVO) {
        String commodityName = commodityVO.getCommodityName();
        // 先查询待上架淘品是否已存在（不能同名）
        Commodity checkCommodity = commodityMapper.selectNameOfCommodity(commodityName);
        // 若存在，则返回失败
        if (checkCommodity != null) {
            return Result.fail("商城上已有同款淘品，无法上架！");
        }
        long commodityId = RandomUtil.randomLong(LONG_RANDOM_START, Long.MAX_VALUE);
        commodityVO.setCommodityId(commodityId);
        Commodity commodity = BeanUtil.copyProperties(commodityVO, Commodity.class);
        save(commodity);
        return Result.success("淘品上架成功！");
    }

    /**
     * 点击商品跳转到详情页（根据商品id查询）
     * @param commodityId 商品id
     * @return 商品
     */
    @Override
    public List<Commodity> queryCommodityById(Long commodityId) {
        return commodityMapper.selectCommodityById(commodityId);
    }
}




