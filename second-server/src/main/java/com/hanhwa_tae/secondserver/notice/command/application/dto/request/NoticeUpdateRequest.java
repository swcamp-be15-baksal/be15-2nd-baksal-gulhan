package com.hanhwa_tae.secondserver.notice.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeUpdateRequest {

    @NotBlank(message = "제목입력필수")
    private String title;

    @NotBlank(message = "내용입력필수")
    private String content;

}
