package com.taotao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.entity.Apply;

/**
* @author YuLong
* @description 针对表【apply(申请表)】的数据库操作Service
* @createDate 2022-12-10 21:42:06
*/
public interface ApplyService extends IService<Apply> {

    /**
     * 查看用户申请商家记录
     * @param userId 用户账号
     * @return 申请记录
     */
    Apply queryApplyRecord(Long userId);

    /**
     * 保存用户申请商家权限记录
     * @param userId 用户账号
     */
    void saveMerchant(Long userId);
}
