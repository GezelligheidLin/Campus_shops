package com.taotao.dto;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/12/5 13:56
 */
@Data
public class OrderDTO {
    private Long userId;
    private Long commodityId;
    private Long buyQuantity;
    private Long orderCost;
}
