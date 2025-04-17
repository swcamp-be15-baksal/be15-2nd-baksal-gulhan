package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequest {

    @NotBlank( message = "내용입력은 필수입니다.")
    private String content;
}
