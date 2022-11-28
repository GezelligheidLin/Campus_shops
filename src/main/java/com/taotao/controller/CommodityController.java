package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2022/11/21 14:20
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/home")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @GetMapping("/carousel")
    public Result searchCommodityCarousel() {
        log.info("首页淘品轮播图查询。。。");
        return commodityService.queryCarousel();
    }

    @GetMapping("/recommend")
    public Result searchCommodityRecommend() {
        log.info("首页淘品甄选推荐查询。。。");
        return commodityService.queryRecommend();
    }

    @GetMapping("/area")
    public Result searchCommodityArea() {
        log.info("首页各大专区选购查询。。。");
        return commodityService.queryArea();
    }

    @GetMapping("/like")
    public Result searchCommodityLike() {
        log.info("首页淘品猜你喜欢查询。。。");
        return commodityService.queryLike();
    }
}
