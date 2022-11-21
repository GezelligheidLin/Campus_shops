package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:27
* @Entity taotao.entity.Commodity
*/
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

}




