package com.hanhwa_tae.gulhan.notice.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class NoticeInsertRequest {
    @NotBlank(message = "제목입력은 필수입니다.")
    private String title;

    @NotBlank(message = "내용입력은 필수입니다.")
    private String content;
}
