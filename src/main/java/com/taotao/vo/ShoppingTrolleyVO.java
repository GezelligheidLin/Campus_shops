package com.taotao.vo;

import cn.hutool.json.JSON;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/30 19:33
 */
@Data
public class ShoppingTrolleyVO {
    private Long trolleyId;
    private Long userId;
    private String description;
    private Long amount;
    private Long totalPrice;
    private JSON goods;
}
