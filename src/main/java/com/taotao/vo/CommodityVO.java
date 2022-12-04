package com.taotao.vo;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/30 13:56
 */
@Data
public class CommodityVO {
    private Long commodityId;
    private String commodityName;
    private String image;
    private String description;
    private String detail;
    private Long price;
    private String evaluate;
    private String commodityType;
    private Long merchantId;
    private Long quantity;
}
