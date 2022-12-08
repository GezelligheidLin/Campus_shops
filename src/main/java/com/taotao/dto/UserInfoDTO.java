package com.taotao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author YuLong
 * Date: 2022/11/21 16:07
 */
@Data
public class UserInfoDTO {
    private Long userId;
    private String oldPhone;
    private String phone;
    private String code;
    private String rawPassword;
    private String password;
    private String focus;
    private Date birthday;
    private String nickName;
    private String icon;
    private String address;
    private Integer isMerchant;
}
