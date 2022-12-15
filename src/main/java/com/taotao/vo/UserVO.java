package com.taotao.vo;

import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/12/3 14:32
 */
@Data
public class UserVO {
    private Long userId;
    private String nickName;
    private String icon;
    private Integer status;
    private Integer isMerchant;
}
