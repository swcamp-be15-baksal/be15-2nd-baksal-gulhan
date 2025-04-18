package com.hanhwa_tae.firstserver.goods.query.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsSearchRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.response.GoodsDTO;
import com.hanhwa_tae.firstserver.goods.query.dto.response.GoodsListResponse;
import com.hanhwa_tae.firstserver.goods.query.service.GoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "기념품")
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsQueryController {

    private final GoodsQueryService goodsQueryService;

    // 목록 조회
    @Operation(summary = "기념품 목록 조회", description = "기념품 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GoodsListResponse>> getGoods(GoodsSearchRequest request) {
        GoodsListResponse response = goodsQueryService.getGoods(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "기념품 상세 조회", description = "해당 기념품을 상세 조회한다.")
    @GetMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<GoodsDTO>> getGoodsDetail(@PathVariable Integer goodsId) {
        GoodsDTO response = goodsQueryService.getGoodsById(goodsId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
