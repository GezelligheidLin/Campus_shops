package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.GoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2022/11/21 14:10
 */
@Slf4j
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @GetMapping("/show")
    public Result searchGoodsType() {
        log.info("首页淘品分类查询。。。");
        return goodsTypeService.queryGoodsClassify();
    }
}
