package com.taotao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.GoodsType;
import com.taotao.mapper.GoodsTypeMapper;
import com.taotao.service.GoodsTypeService;
import org.springframework.stereotype.Service;

/**
* @author YuLong
* @description 针对表【goods_type(商品类型表)】的数据库操作Service实现
* @createDate 2022-11-21 13:58:22
*/
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
    implements GoodsTypeService{

}




