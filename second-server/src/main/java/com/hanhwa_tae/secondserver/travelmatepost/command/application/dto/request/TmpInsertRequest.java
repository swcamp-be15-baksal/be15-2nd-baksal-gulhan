package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TmpInsertRequest {
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    private List<String> imageUrls;
}
