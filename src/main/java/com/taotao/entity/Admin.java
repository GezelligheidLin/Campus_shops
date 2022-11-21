package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员表
 * @author YuLong
 * @createDate 2022-11-21 14:06:42
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 管理员账号
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 管理员真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 管理员状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否为超级管理员
     */
    @TableField(value = "super_admin")
    private Integer superAdmin;

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

    @TableLogic
    private Integer isDeleted;
}