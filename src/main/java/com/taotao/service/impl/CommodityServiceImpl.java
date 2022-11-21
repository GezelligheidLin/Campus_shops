package com.taotao.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Commodity;
import com.taotao.mapper.CommodityMapper;
import com.taotao.service.CommodityService;
import org.springframework.stereotype.Service;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:27
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService {

}




