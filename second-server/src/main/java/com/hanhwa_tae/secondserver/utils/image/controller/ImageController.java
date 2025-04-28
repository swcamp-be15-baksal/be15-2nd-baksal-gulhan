package com.hanhwa_tae.secondserver.utils.image.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/image")
public class ImageController {
    // TODO : 이미지 넣기
    @Operation(summary = "이미지 넣기", description = "이미지 삽입")
    @PutMapping()
    public ResponseEntity<ApiResponse<Void>> uploadImage(
        MultipartFile file
    ){

        return ResponseEntity.ok(ApiResponse.success(null));
    }




    // TODO : 이미지 불러오기
}
