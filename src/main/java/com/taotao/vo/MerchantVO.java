package com.taotao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/29 13:27
 */
@Data
public class MerchantVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private Long merchantId;
    private String nickName;
    private String icon;
    private String fans;
    private Long fansCount;
    private Integer status;
}
