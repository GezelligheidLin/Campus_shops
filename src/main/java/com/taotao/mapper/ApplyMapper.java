package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Apply;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【apply(申请表)】的数据库操作Mapper
* @createDate 2022-12-10 21:42:06
* @Entity com.taotao.entity.Apply
*/
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {


    /**
     * 查看用户申请商家记录
     * @param userId 用户账号
     * @return 申请记录
     */
    Apply selectApplyRecord(Long userId);

    /**
     * 插入用户申请商家记录
     * @param applyId 申请表主键
     * @param userId 用户账号
     */
    void insertUserApply(Long applyId, Long userId);
}




