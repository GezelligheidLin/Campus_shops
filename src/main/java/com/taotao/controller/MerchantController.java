package com.taotao.controller;

import com.taotao.dto.Result;
import com.taotao.service.MerchantService;
import com.taotao.vo.MerchantVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hotRank")
    public Result<List<MerchantVO>> searchHotRank() {
        log.info("商家热力榜查询。。。");
        return Result.success(merchantService.queryListHotRandOfUserInfo());
    }

}
