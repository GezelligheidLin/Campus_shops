package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

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
    @TableId(value = "id")
    private Long id;

    /**
     * 粉丝
     */
    @TableField(value = "fans")
    private String fans;

    /**
     * 粉丝数量
     */
    @TableField(value = "fansCount")
    private Integer fansCount;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}