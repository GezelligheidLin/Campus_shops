package com.taotao.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.dto.PageData;
import com.taotao.entity.Merchant;
import com.taotao.vo.MerchantVO;

import java.util.List;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Service
* @createDate 2022-11-21 14:07:39
*/
public interface MerchantService extends IService<Merchant> {

    /**
     * 查询热力榜
     * @return 热力榜 list
     */
    List<MerchantVO> queryListHotRandOfUserInfo();

    /**
     * 管理员查询商家
     * @param pageData 分页信息
     * @return 商家分页
     */
    Page<Merchant> queryMerchantOfAdmin(PageData pageData);
}
