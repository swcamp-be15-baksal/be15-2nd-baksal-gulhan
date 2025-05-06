package com.hanhwa_tae.secondserver.image.service;


import com.hanhwa_tae.secondserver.image.dto.request.SaveImageRequest;
import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageUploadResponse uploadImage(MultipartFile file) throws Exception;

    void saveImage(SaveImageRequest request);
}
