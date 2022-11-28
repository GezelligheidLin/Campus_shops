package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:39
* @Entity taotao.entity.Merchant
*/
@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {


    /**
     * 查找热力榜
     * @param hotRankSize 热力榜商家数量
     * @return 热力榜商家 list
     */
    List<Merchant> selectHotRank(int hotRankSize);
}




