package com.taotao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YuLong
 * Date: 2022/11/21 16:11
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static <T> Result<T> success() {
        return new Result<>(true, null, null, null);
    }

    public static <T> Result<T> success(Object data) {
        return new Result<>(true, null, data, null);
    }

    public static <T> Result<T> success(List<?> data, Long total) {
        return new Result<>(true, null, data, total);
    }

    public static <T> Result<T> fail(String errorMsg) {
        return new Result<>(false, errorMsg, null, null);
    }
}
