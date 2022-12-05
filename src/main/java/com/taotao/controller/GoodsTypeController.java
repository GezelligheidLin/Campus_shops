package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.GoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2022/11/21 14:10
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("/icon")
    public Result searchAllIcon() {
        log.info("首页全部淘品分类图标查询。。。");
        return goodsTypeService.queryAllIcon();
    }

    @GetMapping("/classify")
    public Result searchLife(@RequestParam String type) {
        log.info("查询{}分类淘品。。。", type);
        return goodsTypeService.queryClassifyGoods(type);
    }

}
