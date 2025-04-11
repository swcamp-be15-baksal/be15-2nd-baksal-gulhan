package com.hanhwa_tae.gulhan.goods.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.goods.command.application.service.GoodsCommandService;
import com.hanhwa_tae.gulhan.goods.query.dto.request.GoodsInsertRequest;
import com.hanhwa_tae.gulhan.goods.query.dto.request.GoodsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/goods")
@RequiredArgsConstructor
public class GoodsCommandController {
    private final GoodsCommandService goodsCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertGoods(@RequestBody GoodsInsertRequest request) {
        return ResponseEntity.ok(ApiResponse.success(goodsCommandService.insertGoods(request)));
    }

    @PutMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<Void>> updateGoods (@PathVariable Integer goodsId, @RequestBody GoodsUpdateRequest request) {
        goodsCommandService.updateGoods(goodsId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<Void>> deleteGoods(@PathVariable Integer goodsId) {
        goodsCommandService.deleteGoods(goodsId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
