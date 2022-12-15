package com.taotao.controller;

import cn.hutool.core.bean.BeanUtil;
import com.taotao.dto.Result;
import com.taotao.entity.Commodity;
import com.taotao.service.CommodityService;
import com.taotao.vo.CommodityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YuLong
 * Date: 2022/11/21 14:20
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/home")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @GetMapping("/carousel")
    public Result<List<Commodity>> searchCommodityCarousel() {
        log.info("首页淘品轮播图查询。。。");
        List<Commodity> commodityList = commodityService.queryCarousel();
        return Result.success(entityConvertToVO(commodityList));
    }

    @GetMapping("/recommend")
    public Result<List<Commodity>> searchCommodityRecommend() {
        log.info("首页淘品甄选推荐查询。。。");
        List<Commodity> commodityList = commodityService.queryRecommend();
        return Result.success(entityConvertToVO(commodityList));
    }

    @GetMapping("/area")
    public Result<List<Commodity>> searchCommodityArea() {
        log.info("首页各大专区选购查询。。。");
        List<Commodity> commodityList = commodityService.queryArea();
        return Result.success(entityConvertToVO(commodityList));
    }

    @GetMapping("/like")
    public Result<List<Commodity>> searchCommodityLike() {
        log.info("首页淘品猜你喜欢查询。。。");
        List<Commodity> commodityList = commodityService.queryLike();
        return Result.success(entityConvertToVO(commodityList));
    }

    @GetMapping("/seek/{key}")
    public Result<List<Commodity>> searchCommodityKeyword(@PathVariable("key") String key) {
        log.info("首页商品搜索查询。。。");
        if (key == null || key.isEmpty()) {
            return Result.fail("请输入搜索关键字");
        }
        List<Commodity> commodityList = commodityService.queryKeyword(key);
        return Result.success(commodityList);
    }

    /**
     * 将商品 list转换为商品视图对象 list
     * @param list 商品 list
     * @return 商品视图对象 list
     */
    private List<CommodityVO> entityConvertToVO(List<Commodity> list) {
        return list.stream().map( (item) ->
                BeanUtil.copyProperties(item, CommodityVO.class)
        ).collect(Collectors.toList());
    }
}
