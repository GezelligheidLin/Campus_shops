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
    @TableId(value = "id")
    private Long id;

    /**
     * 商品名
     */
    @TableField(value = "commodity_name")
    private String commodityName;

    /**
     * 商品详情
     */
    @TableField(value = "list_asp")
    private String listAsp;

    /**
     * 商品简略信息
     */
    @TableField(value = "cif")
    private String cif;

    /**
     * 价格
     */
    @TableField(value = "price")
    private Double price;

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
    @TableField(value = "user_otp")
    private Long userOtp;

    /**
     * 商品数量
     */
    @TableField(value = "qoc")
    private Long qoc;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}