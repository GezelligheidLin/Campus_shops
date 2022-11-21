package com.taotao.util;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuLong
 * Date: 2022/11/21 15:36
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
