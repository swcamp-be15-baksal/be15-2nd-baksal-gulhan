package com.hanhwa_tae.gulhan.goods.query.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    String selectGoodsNameById(Integer goods_id);
}
