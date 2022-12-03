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
    private String cardId;
    private String phone;
    private String password;
    private String realName;
    private String sex;
    private String focus;
    private String address;
    private Date birthday;
    private String nickName;
    private String icon;
    private Integer isMerchant;
}
