package com.taotao.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.Result;
import com.taotao.dto.UserDTO;
import com.taotao.entity.Merchant;
import com.taotao.entity.User;
import com.taotao.mapper.MerchantMapper;
import com.taotao.service.MerchantService;
import com.taotao.service.UserService;
import com.taotao.vo.MerchantVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:39
*/
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
    implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

    @Resource
    private UserService userService;

    private final static int RANK_SIZE = 20;

    /**
     * 查询热力榜
     * @return 热力榜 list
     */
    @Override
    public Result queryHotRank() {
        // 1.查找商家信息
        List<Merchant> merchantList = merchantMapper.selectHotRank(RANK_SIZE);
        // 2.根据查询到的商家去获取 id列表
        List<Long> ids = merchantList.stream().map(Merchant::getId).collect(Collectors.toList());
        // 3.根据 id查询该商家所属的用户信息 list
        List<User> userList = userService.queryHotRankOfUserInfo(ids);
        // 4.将 userList中信息 拷贝到 userDTOList中
        List<UserDTO> userDTOList = userList.stream().map( (item) ->
                BeanUtil.copyProperties(item, UserDTO.class)
        ).collect(Collectors.toList());
        // 5.将 userDTOList和 merchantList 分别拷贝给 merchant
        List<MerchantVO> merchantVOList = new ArrayList<>();
        int size = userDTOList.size();
        for(int i = 0; i < size; i++){
            MerchantVO merchantVO = new MerchantVO();
            BeanUtil.copyProperties(userDTOList.get(i), merchantVO, true);
            BeanUtil.copyProperties(merchantList.get(i), merchantVO, true);
            merchantVOList.add(merchantVO);
        }
        // 6.返回商家热力榜信息
        return Result.success(merchantVOList);
    }
}




