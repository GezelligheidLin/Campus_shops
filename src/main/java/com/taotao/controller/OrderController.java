package com.taotao.controller;

import com.taotao.dto.OrderDTO;
import com.taotao.dto.Result;
import com.taotao.service.OrderService;
import com.taotao.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YuLong
 * Date: 2022/11/21 14:11
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/save")
    public Result<String> generateOrder(@RequestBody OrderDTO orderDTO) {
        log.info("为用户生成订单中。。。");
        orderService.saveOrder(orderDTO);
        return Result.success("购买成功！");
    }

    @GetMapping("/view/{userId}")
    public Result<List<OrderVO>> viewOrder(@PathVariable("userId") Long userId) {
        log.info("用户查看订单中。。。");
        List<OrderVO> orderVOList = orderService.observeOrder(userId);
        log.info("order = {}", orderVOList);
        return Result.success(orderVOList);
    }
}
