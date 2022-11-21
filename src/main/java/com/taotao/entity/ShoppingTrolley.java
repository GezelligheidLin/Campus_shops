package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id")
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "user_id")
    private Integer user_id;

    /**
     * 商品简略信息
     */
    @TableField(value = "cif")
    private String cif;

    /**
     * 加入购物车的商品数量
     */
    @TableField(value = "count")
    private Integer count;

}