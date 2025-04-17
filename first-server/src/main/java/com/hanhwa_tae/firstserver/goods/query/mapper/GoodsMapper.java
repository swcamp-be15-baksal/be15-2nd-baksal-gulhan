package com.hanhwa_tae.firstserver.goods.query.mapper;


import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsSearchRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.response.GoodsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<GoodsDTO> selectGoods(GoodsSearchRequest request);
    long countGoods(GoodsSearchRequest request);
    GoodsDTO selectGoodsById(Integer goodsId);

    String selectGoodsNameById(int targetId);

}
