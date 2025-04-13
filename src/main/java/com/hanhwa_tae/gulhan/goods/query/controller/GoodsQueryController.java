package com.hanhwa_tae.gulhan.goods.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.goods.query.dto.request.GoodsSearchRequest;
import com.hanhwa_tae.gulhan.goods.query.dto.response.GoodsDTO;
import com.hanhwa_tae.gulhan.goods.query.dto.response.GoodsListResponse;
import com.hanhwa_tae.gulhan.goods.query.service.GoodsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/goods")
@RequiredArgsConstructor
public class GoodsQueryController {

    private final GoodsQueryService goodsQueryService;

    // 목록 조회
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GoodsListResponse>> getGoods(GoodsSearchRequest request) {
        GoodsListResponse response = goodsQueryService.getGoods(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<GoodsDTO>> getGoodsDetail(@PathVariable Integer goodsId) {
        GoodsDTO response = goodsQueryService.getGoodsById(goodsId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
