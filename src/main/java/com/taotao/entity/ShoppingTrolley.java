package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName shopping_trolley
 */
@TableName(value ="shopping_trolley")
@Data
public class ShoppingTrolley implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "trolley_id")
    private Long trolleyId;

    /**
     * 用户账号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 购物车中所有商品
     */
    @TableField(value = "goods")
    private String goods;

    /**
     * 加入购物车的商品数量
     */
    @TableField(value = "amount")
    private Long amount;

    /**
     * 购物车商品总价格
     */
    @TableField(value = "total_price")
    private Long totalPrice;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}