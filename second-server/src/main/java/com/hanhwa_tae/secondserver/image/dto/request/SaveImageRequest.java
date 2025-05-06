package com.hanhwa_tae.secondserver.image.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SaveImageRequest {
    List<String> imageList;
}
