package com.hanhwa_tae.secondserver.image.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(MultipartFile file) throws Exception;
}
