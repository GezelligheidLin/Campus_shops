package com.taotao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YuLong
* @description 针对表【order(订单表)】的数据库操作Mapper
* @createDate 2022-11-21 14:07:49
* @Entity taotao.entity.ShopOrder
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




