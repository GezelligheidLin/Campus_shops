package com.taotao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Order;
import com.taotao.mapper.OrderMapper;
import com.taotao.service.OrderService;
import org.springframework.stereotype.Service;

/**
* @author YuLong
* @description 针对表【shop_order(订单表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:49
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService {

}




