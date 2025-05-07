package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TmpInsertRequest {

    private String title;

    private String content;

    private List<String> imageUrls;
}
