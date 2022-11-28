package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * 密码，加密存储
     */
    @TableField(value = "password")
    private String password;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String nickName;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 关注的店铺
     */
    @TableField(value = "attention")
    private String attention;

    /**
     * 用户身份证
     */
    @TableField(value = "card_id")
    private String cardId;

    /**
     * 用户真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 用户头像url
     */
    @TableField(value = "profile_photo")
    private String profilePhoto;

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