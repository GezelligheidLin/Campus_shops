package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @TableId(value = "good_type_id")
    private Long goodTypeId;

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