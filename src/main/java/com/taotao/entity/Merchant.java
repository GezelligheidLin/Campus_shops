package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商家表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName merchant
 */
@TableName(value ="merchant")
@Data
public class Merchant implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "merchant_id")
    private Long merchantId;

    /**
     * 用户账号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 粉丝
     */
    @TableField(value = "fans")
    private String fans;

    /**
     * 粉丝数量
     */
    @TableField(value = "fansCount")
    private Long fansCount;

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