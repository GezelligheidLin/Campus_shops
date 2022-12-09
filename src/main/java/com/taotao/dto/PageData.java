package com.taotao.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author YuLong
 * Date: 2022/12/8 20:47
 */
@Data
public class PageData {
    private Integer page;
    private Integer pageSize;
    private String key;
    private Map<String, List<Boolean>> sortMap;
}
