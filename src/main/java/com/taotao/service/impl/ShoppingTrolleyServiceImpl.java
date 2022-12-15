package com.taotao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.ShoppingTrolley;
import com.taotao.mapper.ShoppingTrolleyMapper;
import com.taotao.service.ShoppingTrolleyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
* @author YuLong
* @description 针对表【shopping_trolley(购物车表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:58
*/
@Slf4j
@Service
public class ShoppingTrolleyServiceImpl extends ServiceImpl<ShoppingTrolleyMapper, ShoppingTrolley>
    implements ShoppingTrolleyService {

    @Resource
    private ShoppingTrolleyMapper trolleyMapper;

    /**
     * 查询购物车
     * @param userId 用户账号
     * @return 购物车信息
     */
    @Override
    public ShoppingTrolley queryTrolley(Long userId) {
        return trolleyMapper.selectTrolley(userId);
    }

    /**
     * 更新购物车
     * @param trolley 购物车
     */
    @Override
    public void modifyTrolley(ShoppingTrolley trolley) {
        trolley.setUpdateTime(LocalDateTime.now());
        trolleyMapper.updateTrolley(trolley);
    }
}




