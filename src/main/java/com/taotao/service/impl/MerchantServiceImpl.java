package com.taotao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.Result;
import com.taotao.entity.Merchant;
import com.taotao.mapper.MerchantMapper;
import com.taotao.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:39
*/
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
    implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

    private final static int RANK_SIZE = 20;

    /**
     * 查询热力榜
     * @return 热力榜 list
     */
    @Override
    public Result queryHotRank() {
        return Result.success(merchantMapper.selectHotRank(RANK_SIZE));
    }
}




