package com.taotao.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.taotao.util.SystemConstants.CREATE_TIME;
import static com.taotao.util.SystemConstants.UPDATE_TIME;

/**
 * @author YuLong
 * Date: 2022/11/24 10:27
 * 自定义对象处理器（处理公共字段填充）
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入字段，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue(CREATE_TIME, LocalDateTime.now());
        metaObject.setValue(UPDATE_TIME, LocalDateTime.now());
    }

    /**
     * 更新字段，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段填充[update]...");
        log.info(metaObject.toString());
        metaObject.setValue(UPDATE_TIME, LocalDateTime.now());
    }
}
