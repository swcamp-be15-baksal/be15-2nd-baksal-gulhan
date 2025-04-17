package com.hanhwa_tae.firstserver.goods.query.service;


import com.hanhwa_tae.firstserver.common.dto.Pagination;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.common.exception.custom.PageNotFoundException;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsSearchRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.response.GoodsDTO;
import com.hanhwa_tae.firstserver.goods.query.dto.response.GoodsListResponse;
import com.hanhwa_tae.firstserver.goods.query.mapper.GoodsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsQueryService {
    private final GoodsMapper goodsMapper;

    @Transactional(readOnly = true)
    public GoodsListResponse getGoods(GoodsSearchRequest goodsSearchRequest) {
        goodsSearchRequest.calculatePaging();

        List<GoodsDTO> goods = goodsMapper.selectGoods(goodsSearchRequest);
        long totalGoods = goodsMapper.countGoods(goodsSearchRequest);

        int page = goodsSearchRequest.getPage();
        int size = goodsSearchRequest.getSize();

        return GoodsListResponse.builder()
                .goods(goods)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int)Math.ceil((double)totalGoods/size))
                        .size(size)
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public GoodsDTO getGoodsById(Integer goodsId) {
        GoodsDTO dto = goodsMapper.selectGoodsById(goodsId);
        if (dto != null) {
            dto.setRemaining(dto.getQuantity() - dto.getSold());
        } else {
            throw new PageNotFoundException(ErrorCode.PAGE_NOT_FOUND);
        }
        dto.setAvgRating(dto.getAvgRating());
        return dto;
    }


}
