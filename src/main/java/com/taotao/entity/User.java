package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 账号，主键
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 用户身份证
     */
    @TableField(value = "card_id")
    private String cardId;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 密码，加密存储
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 关注的店铺
     */
    @TableField(value = "focus")
    private String focus;

    /**
     * 用户地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户头像url
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private Integer status;

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
     * 是否为商家
     */
    @TableField(value = "is_merchant")
    private Integer isMerchant;

    /**
     * 是否已实名
     */
    @TableField(value = "is_real")
    private Integer isReal;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;
}