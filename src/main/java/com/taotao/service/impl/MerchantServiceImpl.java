package com.taotao.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.PageData;
import com.taotao.dto.Result;
import com.taotao.entity.Merchant;
import com.taotao.entity.User;
import com.taotao.mapper.MerchantMapper;
import com.taotao.service.CommodityService;
import com.taotao.service.MerchantService;
import com.taotao.service.UserService;
import com.taotao.vo.CommodityVO;
import com.taotao.vo.MerchantVO;
import com.taotao.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.taotao.util.SystemConstants.*;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:39
*/
@Slf4j
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
    implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private UserService userService;

    @Resource
    private CommodityService commodityService;

    private final static int RANK_SIZE = 20;

    /**
     * 查询热力榜
     * @return 热力榜 list
     */
    @Override
    public List<MerchantVO> queryListHotRandOfUserInfo() {
        // 1.查找商家信息
        List<Merchant> merchantList = merchantMapper.selectHotRank(RANK_SIZE);
        // 2.根据查询到的商家去获取 id列表
        List<Long> ids = merchantList.stream().map(Merchant::getUserId).collect(Collectors.toList());
        // 3.根据 id查询该商家所属的用户信息 list
        List<User> userList = new ArrayList<>();
        // userList = userServiceImpl.queryListHotRankOfUserInfo(ids);
        for (int i = 0; i < RANK_SIZE; i++) {
            userList.add(userService.querySingleHotRankOfUserInfo(ids.get(i)));
        }
        // 4.将 userList中信息 拷贝到 userDTOList中
        List<UserVO> userVOList = userList.stream().map( (item) ->
                BeanUtil.copyProperties(item, UserVO.class)
        ).collect(Collectors.toList());
        // 5.将 userDTOList和 merchantList 分别拷贝给 merchant
        List<MerchantVO> merchantVOList = new ArrayList<>();
        int size = userVOList.size();
        for(int i = 0; i < size; i++){
            MerchantVO merchantVO = new MerchantVO();
            BeanUtil.copyProperties(userVOList.get(i), merchantVO, true);
            BeanUtil.copyProperties(merchantList.get(i), merchantVO, true);
            merchantVOList.add(merchantVO);
        }
        // 6.返回商家热力榜信息
        return merchantVOList;
    }

    /**
     * 管理员查询商家 （adminService调用该方法）
     * @param pageData 分页信息
     * @return 商家分页
     */
    @Override
    public Page<Merchant> queryMerchantOfAdmin(PageData pageData) {
        // 获取到前端发送过来的分页数据
        Integer page = pageData.getPage();
        Integer pageSize = pageData.getPageSize();
        String key = pageData.getKey();
        Map<String, List<Boolean>> sortMap = pageData.getSortMap();
        log.info("page = {}, pageSize = {}, key = {}, sortMap = {}", page, pageSize, key, sortMap);
        // 从sortMap中拿取到排序条件
        List<Boolean> fansCountSort = sortMap.get(FANS_COUNT_SORT);
        List<Boolean> createTimeSort = sortMap.get(CREATE_TIME_SORT);
        Boolean fansCountSortStatus = fansCountSort.get(LIST_FIRST_INDEX);
        Boolean fansCountSortOrder = fansCountSort.get(LIST_SECOND_INDEX);
        Boolean createTimeSortStatus = createTimeSort.get(LIST_FIRST_INDEX);
        Boolean createTimeSortOrder = createTimeSort.get(LIST_SECOND_INDEX);
        // 构造分页器
        Page<Merchant> pageInfo = new Page<>(page, pageSize);
        // 用 LambdaQueryWrapper 查询并按条件进行排序
        LambdaQueryWrapper<Merchant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Merchant::getStoreName, key)
                .orderBy(fansCountSortStatus,
                fansCountSortOrder, Merchant::getFansCount)
                .orderBy(createTimeSortStatus,
                createTimeSortOrder, Merchant::getCreateTime);
        // 将 LambdaQueryWrapper中数据赋给 pageInfo
        page(pageInfo, queryWrapper);
        // 返回分页数据
        return pageInfo;
    }

    /**
     * 管理员修改商家状态
     * @param merchantVO 商家状态信息
     */
    @Override
    public void modifyMerchantStatusOfAdmin(MerchantVO merchantVO) {
        merchantMapper.updateMerchantStatus(merchantVO);
    }

    /**
     * 保存淘品信息中间传输方法（数据传输 adminService -> merchantService）
     * @param commodityVO 淘品视图对象
     * @return Result
     */
    @Override
    public Result<String> saveGoodsOfMerchantWithTransmitData(CommodityVO commodityVO) {
        Long merchantId = merchantMapper.selectMerchantId(commodityVO.getUserId());
        commodityVO.setMerchantId(merchantId);
        return commodityService.saveGoods(commodityVO);
    }

}




