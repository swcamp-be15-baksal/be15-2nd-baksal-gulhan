package com.hanhwa_tae.secondserver.image.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import com.hanhwa_tae.secondserver.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    // TODO : 이미지 넣기
    @Operation(summary = "이미지 넣기", description = "이미지 삽입")
    @PostMapping()
    public ResponseEntity<ApiResponse<ImageUploadResponse>> uploadImage(
        @RequestPart("image") MultipartFile file
    ) throws Exception {
        ImageUploadResponse response = imageService.uploadImage(file);

        return ResponseEntity.ok(ApiResponse.success(response));
    }




    // TODO : 이미지 불러오기
}
