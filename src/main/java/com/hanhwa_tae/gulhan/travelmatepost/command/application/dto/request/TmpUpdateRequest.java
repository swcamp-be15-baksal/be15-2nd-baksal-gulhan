package com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TmpUpdateRequest {
    @NotBlank
    private final String title;
    @NotBlank
    private final String content;

    private final String isDeleted;

}
