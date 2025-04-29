package com.hanhwa_tae.secondserver.image.service;


import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageUploadResponse uploadImage(MultipartFile file) throws Exception;
}
