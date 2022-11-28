package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * 管理员账号，主键
     */
    @TableId(value = "admin_id")
    private Long adminId;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

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
    @TableField(value = "is_root")
    private Integer isRoot;

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