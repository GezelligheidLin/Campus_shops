package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 商品数量
     */
    @TableField(value = "quantity")
    private Long quantity;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}