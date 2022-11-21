package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 密码
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
    private String birthday;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private Integer phone;

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
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

}