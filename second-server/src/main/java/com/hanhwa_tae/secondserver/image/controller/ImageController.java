package com.hanhwa_tae.secondserver.image.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.image.dto.request.SaveImageRequest;
import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import com.hanhwa_tae.secondserver.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    // TODO : 이미지 넣기
    @Operation(summary = "이미지 임시 저장", description = "이미지 임시 저장")
    @PostMapping("/temp")
    public ResponseEntity<ApiResponse<ImageUploadResponse>> uploadImage(
        @RequestPart("image") MultipartFile file
    ) throws Exception {
        ImageUploadResponse response = imageService.uploadImage(file);

        return ResponseEntity.ok(ApiResponse.success(response));
    }




    // TODO : 이미지 저장 확정
    @RequestMapping("/save")
    public ResponseEntity<ApiResponse<Void>> saveImage(
            @RequestBody SaveImageRequest request
    ){
        imageService.saveImage(request);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
