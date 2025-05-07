package com.hanhwa_tae.firstserver.client;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SaveImageRequest {
    List<String> imageList;
}
