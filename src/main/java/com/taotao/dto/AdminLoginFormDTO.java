package com.taotao.dto;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/24 15:34
 */
@Data
public class AdminLoginFormDTO {
    private Long id;
    private String password;
    private String phone;
    private String code;
    private Integer status;
    private Integer isRoot;
}
