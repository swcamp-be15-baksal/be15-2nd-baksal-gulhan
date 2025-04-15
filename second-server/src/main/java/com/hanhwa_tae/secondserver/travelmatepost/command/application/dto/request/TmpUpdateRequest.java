package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class TmpUpdateRequest {

    @NotBlank(message = "제목을 입력하세요")
    private final String title;
    @NotBlank(message = "내용을 입력하세요.")
    private final String content;

}
