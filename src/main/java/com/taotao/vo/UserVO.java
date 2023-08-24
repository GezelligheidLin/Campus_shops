package com.taotao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/12/3 14:32
 */
@Data
public class UserVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String nickName;
    private String icon;
    private Integer status;
    private Integer isMerchant;
}
