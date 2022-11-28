package com.taotao.dto;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/11/21 16:10
 */
@Data
public class UserLoginFormDTO {
    private String phone;
    private String code;
    private String password;
}
