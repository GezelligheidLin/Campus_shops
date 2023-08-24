package com.taotao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.entity.Commodity;
import com.taotao.entity.GoodsType;
import com.taotao.mapper.GoodsTypeMapper;
import com.taotao.service.GoodsTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author YuLong
* @description 针对表【goods_type(商品类型表)】的数据库操作Service实现
* @createDate 2022-11-21 13:58:22
*/
@Slf4j
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
    implements GoodsTypeService{

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    private static final int ICON_SIZE = 10;

    private static final int CLASSIFY_GOODS_SIZE = 10;

    /**
     * 查询全部分类图标
     * @return 分类图标 list
     */
    @Override
    public List<GoodsType> queryAllIcon() {
        return goodsTypeMapper.selectAllIcon(ICON_SIZE);
    }

    /**
     * 查询分类淘品
     * @param type 分类
     * @return 分类淘品 list
     */
    @Override
    public List<Commodity> queryClassifyGoods(String type) {
        return goodsTypeMapper.selectClassifyGoods(type, CLASSIFY_GOODS_SIZE);
    }
}




