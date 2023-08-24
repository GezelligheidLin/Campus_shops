package com.taotao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author YuLong
 * Date: 2022/12/7 8:50
 */
@Data
public class UserRealVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String realName;
    private String cardId;
}
