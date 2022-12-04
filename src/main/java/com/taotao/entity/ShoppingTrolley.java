package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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