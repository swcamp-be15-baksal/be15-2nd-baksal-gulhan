package com.hanhwa_tae.firstserver.goods.command.application.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.goods.command.application.service.GoodsCommandService;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsInsertRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "기념품")
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsCommandController {
    private final GoodsCommandService goodsCommandService;

    @Operation(summary = "기념품 등록", description = "새로운 기념품을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertGoods(
            @RequestBody GoodsInsertRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(goodsCommandService.insertGoods(request)));
    }

    @Operation(summary = "기념품 수정", description = "수정할 기념품을 수정한다.")
    @PutMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<Void>> updateGoods (
            @PathVariable Integer goodsId,
            @RequestBody GoodsUpdateRequest request
    ) {
        goodsCommandService.updateGoods(goodsId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "기념품 삭제", description = "삭제할 기념품을 삭제한다. soft delete")
    @DeleteMapping("/list/{goodsId}")
    public ResponseEntity<ApiResponse<Void>> deleteGoods(
            @PathVariable Integer goodsId
    ) {
        goodsCommandService.deleteGoods(goodsId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
