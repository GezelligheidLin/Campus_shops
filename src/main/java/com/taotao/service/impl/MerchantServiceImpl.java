package com.taotao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Merchant;
import com.taotao.mapper.MerchantMapper;
import com.taotao.service.MerchantService;
import org.springframework.stereotype.Service;

/**
* @author YuLong
* @description 针对表【merchant(商家表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:39
*/
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant>
    implements MerchantService {

}




