package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* description 针对表【goods_type(商品类型表)】的数据库操作Mapper
* createDate 2022-11-21 13:58:22
* Entity taotao.entity.GoodsType
*/
@Mapper
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

}




