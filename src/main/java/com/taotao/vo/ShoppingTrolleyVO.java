package com.taotao.vo;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/30 19:33
 */
@Data
public class ShoppingTrolleyVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long trolleyId;
    private Long userId;
    private Long amount;
    private Long totalPrice;
    private JSON goods;
}
