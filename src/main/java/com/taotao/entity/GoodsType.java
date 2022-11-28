package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品类型表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName goods_type
 */
@TableName(value ="goods_type")
@Data
public class GoodsType implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 商品类型图标
     */
    @TableField(value = "url")
    private String url;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}