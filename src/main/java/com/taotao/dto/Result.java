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
    private String eventMsg;

    public static Result success() {
        return new Result(true, null, null, null, null);
    }

    public static Result success(Object data) {
        return new Result(true, null, data, null, null);
    }

    public static Result success(List<?> data, Long total) {
        return new Result(true, null, data, total, null);
    }

    public static Result fail(String errorMsg) {
        return new Result(false, errorMsg, null, null, null);
    }

    public static Result event(String eventMsg, Object data) {
        return new Result(false, null, data, null, eventMsg);
    }

    public static Result event(String eventMsg) {
        return new Result(false, null, null, null, eventMsg);
    }
}
