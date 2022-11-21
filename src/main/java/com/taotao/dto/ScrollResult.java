package com.taotao.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YuLong
 * Date: 2022/11/21 16:19
 */
@Data
public class ScrollResult {
    private List<?> list;
    private Long minTime;
    private Integer offset;
}
