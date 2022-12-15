package com.taotao.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.PageData;
import com.taotao.entity.Apply;
import com.taotao.mapper.ApplyMapper;
import com.taotao.service.ApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.taotao.util.SystemConstants.*;

/**
* @author YuLong
* @description 针对表【apply(申请表)】的数据库操作Service实现
* @createDate 2022-12-10 21:42:06
*/
@Slf4j
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

    /**
     * 查询申请分页
     * @param pageData 分页信息
     * @return 申请分页
     */
    @Override
    public Page<Apply> queryApplyPage(PageData pageData) {
        // 获取到前端发送过来的分页数据
        Integer page = pageData.getPage();
        Integer pageSize = pageData.getPageSize();
        String key = pageData.getKey();
        Map<String, List<Boolean>> sortMap = pageData.getSortMap();
        log.info("page = {}, pageSize = {}, key = {}, sortMap = {}", page, pageSize, key, sortMap);
        // 从sortMap中拿取到排序条件
        List<Boolean> createTimeSort = sortMap.get(CREATE_TIME_SORT);
        Boolean createTimeSortStatus = createTimeSort.get(LIST_FIRST_INDEX);
        Boolean createTimeSortOrder = createTimeSort.get(LIST_SECOND_INDEX);
        // 构造分页器
        Page<Apply> pageInfo = new Page<>(page, pageSize);
        // 用 LambdaQueryWrapper 查询并按条件进行排序
        LambdaQueryWrapper<Apply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Apply::getCreateTime, key)
                .orderBy(createTimeSortStatus,
                        createTimeSortOrder, Apply::getCreateTime);
        // 将 LambdaQueryWrapper中数据赋给 pageInfo
        page(pageInfo, queryWrapper);
        // 返回分页数据
        return pageInfo;
    }
}




