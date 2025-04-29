package com.hanhwa_tae.secondserver.image.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageUploadResponse {

    private String imageUrl;
}
