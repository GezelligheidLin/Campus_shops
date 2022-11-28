package com.taotao.service.impl;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.dto.Result;
import com.taotao.entity.Commodity;
import com.taotao.mapper.CommodityMapper;
import com.taotao.service.CommodityService;
import org.springframework.stereotype.Service;
import static com.taotao.util.SystemConstants.*;

import java.util.ArrayList;
import java.util.List;

/**
* @author YuLong
* @description 针对表【commodity(商品表)】的数据库操作Service实现
* @createDate 2022-11-21 14:07:27
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService {

    /**
     * 首页查询商品列表
     * @return
     */
    @Override
    public Result queryList() {
        // TODO
        final int size = 4;
        List<Commodity> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            long id = RandomUtil.randomLong(1, 10);
            list.add(query().eq(QUERY_COMMODITY_ID, id).one());
        }

        return Result.success(list);
    }


}




