package com.taotao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.entity.Commodity;
import com.taotao.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author YuLong
* description 针对表【goods_type(商品类型表)】的数据库操作Mapper
* createDate 2022-11-21 13:58:22
* Entity taotao.entity.GoodsType
*/
@Mapper
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    /**
     * 查询全部分类图标
     * @param iconSize 分类图标数量
     * @return 分类列表
     */
    List<GoodsType> selectAllIcon(int iconSize);

    /**
     * 查询分类淘品
     * @param type 分类
     * @param classifyGoodsSize 分类商品数量
     * @return 分类淘品 list
     */
    List<Commodity> selectClassifyGoods(String type, int classifyGoodsSize);
}




