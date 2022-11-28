package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2022/11/21 14:20
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;
    @GetMapping("/Home")
    public Result queryCommodityList() {
        log.info("首页查询商品列表");
        return commodityService.queryList();
    }
}
