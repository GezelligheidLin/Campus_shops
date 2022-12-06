package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName commodity
 */
@TableName(value ="commodity")
@Data
public class Commodity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "commodity_id")
    private Long commodityId;

    /**
     * 商品名
     */
    @TableField(value = "commodity_name")
    private String commodityName;

    /**
     * 商品图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 商品简略描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 商品详情
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 价格
     */
    @TableField(value = "price")
    private Long price;

    /**
     * 评价
     */
    @TableField(value = "evaluate")
    private String evaluate;

    /**
     * 商品所属类别
     */
    @TableField(value = "commodity_type")
    private String commodityType;

    /**
     * 商品所属用户账号
     */
    @TableField(value = "merchant_id")
    private Long merchantId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}