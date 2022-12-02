package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName shop_order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 用户账号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 所有已购买的商品
     */
    @TableField(value = "shopped")
    private String shopped;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

}