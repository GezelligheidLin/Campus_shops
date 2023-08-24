package com.taotao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/12/5 14:57
 */
@Data
public class OrderVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;
    private Long userId;
    private String address;
    private String nickName;
    private Long commodityId;
    private String commodityName;
    private String description;
    private Long price;
    private Long buyQuantity;
    private Long totalPrice;
    private Long merchantId;
    private String storeName;
}
