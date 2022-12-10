package com.taotao.dto;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/24 21:11
 */
@Data
public class AdminDTO {
    private Long adminId;
    private String realName;
    private String adminIcon;
    private String sex;
    private Integer status;
    private Integer isRoot;
}
