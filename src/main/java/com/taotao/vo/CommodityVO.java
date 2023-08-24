package com.taotao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/30 13:56
 */
@Data
public class CommodityVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private Long commodityId;
    private String commodityName;
    private String image;
    private String description;
    private String detail;
    private Long price;
    private String commodityType;
    private Long merchantId;
}
