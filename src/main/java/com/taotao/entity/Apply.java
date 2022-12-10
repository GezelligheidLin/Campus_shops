package com.taotao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请表
 * @author YuLong
 * @createDate 2022/12/10 19:12
 * @TableName apply
 */
@TableName(value ="apply")
@Data
public class Apply implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Apply(Long applyId, Long userId) {
        this.applyId = applyId;
        this.userId = userId;
    }

    /**
     * 主键
     */
    @TableId(value = "apply_id")
    private Long applyId;

    /**
     * 用户账号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 申请是否已经通过
     */
    @TableField(value = "is_passed")
    private Integer isPassed;

    /**
     * 是否能够申请
     */
    @TableField(value = "enable")
    private Integer enable;

    /**
     * 申请创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 申请更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否为逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}