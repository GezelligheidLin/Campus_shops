package com.taotao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.OrderDTO;
import com.taotao.entity.Order;
import com.taotao.vo.OrderVO;

import java.util.List;

/**
* @author YuLong
* @description 针对表【shop_order(订单表)】的数据库操作Service
* @createDate 2022-11-21 14:07:49
*/
public interface OrderService extends IService<Order> {

    /**
     * 保存订单
     * @param orderDTO 订单DTO
     */
    void saveOrder(OrderDTO orderDTO);

    /**
     * 查看订单
     * @param userId 用户账号
     * @return 订单视图对象 list
     */
    List<OrderVO> observeOrder(Long userId);
}
