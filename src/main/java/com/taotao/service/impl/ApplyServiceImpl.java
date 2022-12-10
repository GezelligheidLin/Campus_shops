package com.taotao.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Apply;
import com.taotao.mapper.ApplyMapper;
import com.taotao.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.taotao.util.SystemConstants.LONG_RANDOM_START;

/**
* @author YuLong
* @description 针对表【apply(申请表)】的数据库操作Service实现
* @createDate 2022-12-10 21:42:06
*/
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply>
    implements ApplyService{

    @Resource
    private ApplyMapper applyMapper;

    /**
     * 查看用户申请商家记录
     * @param userId 用户账号
     * @return 申请记录
     */
    @Override
    public Apply queryApplyRecord(Long userId) {
        return applyMapper.selectApplyRecord(userId);
    }

    /**
     * 保存用户申请商家权限记录
     * @param userId 用户账号
     */
    @Override
    public void saveMerchant(Long userId) {
        long applyId = RandomUtil.randomLong(LONG_RANDOM_START, Long.MAX_VALUE);
        Apply apply = new Apply(applyId, userId);
        // 调用 MyBatis-Plus的 save方法进行保存，达到公共字段填充的目的
        save(apply);
    }
}




