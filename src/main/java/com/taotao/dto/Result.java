package com.taotao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YuLong
 * Date: 2022/11/21 16:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static Result success() {
        return new Result(true, null, null, null);
    }

    public static Result success(Object data) {
        return new Result(true, null, data, null);
    }

    public static Result success(List<?> data, Long total) {
        return new Result(true, null, data, total);
    }

    public static Result fail(String errorMsg) {
        return new Result(false, errorMsg, null, null);
    }
}
