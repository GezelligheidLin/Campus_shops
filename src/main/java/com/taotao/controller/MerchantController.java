package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.MerchantService;
import com.taotao.vo.CommodityVO;
import com.taotao.vo.MerchantVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YuLong
 * Date: 2022/11/21 14:10
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Resource
    private MerchantService merchantService;

    /**
     * 查询商家热力榜（按照粉丝数排行）
     *
     * @return 商家 list
     */
    @GetMapping("/hotRank")
    public Result<List<MerchantVO>> searchHotRank() {
        log.info("商家热力榜查询。。。");
        return Result.success(merchantService.queryListHotRandOfUserInfo());
    }

    /**
     * 商家上架淘品
     *
     * @param commodityVO 淘品视图对象
     * @return Result
     */
    @PutMapping("/putaway")
    public Result<String> putawayGoods(@RequestBody CommodityVO commodityVO) {
        log.info("商家上架淘品中。。。");
        if (commodityVO.getCommodityName() == null || commodityVO.getDescription() == null
                || commodityVO.getDetail() == null || commodityVO.getPrice() == null
                || commodityVO.getEvaluate() == null || commodityVO.getCommodityType() == null
                || commodityVO.getMerchantId() == null){
            return Result.fail("待上架淘品信息不全，上架失败");
        }
        return merchantService.saveGoodsOfMerchantWithTransmitData(commodityVO);
    }

}
