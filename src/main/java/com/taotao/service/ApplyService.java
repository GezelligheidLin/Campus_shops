package com.taotao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.PageData;
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

    /**
     * 查询申请分页
     * @param pageData 分页信息
     * @return 申请分页
     */
    Page<Apply> queryApplyPage(PageData pageData);
}
