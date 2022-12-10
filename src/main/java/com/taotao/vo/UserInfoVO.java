package com.taotao.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author YuLong
 * Date: 2022/12/10 13:26
 */
@Data
public class UserInfoVO {
    private Long userId;
    private String phone;
    private String focus;
    private Date birthday;
    private String nickName;
    private String icon;
    private String address;
    private Integer isMerchant;
}
