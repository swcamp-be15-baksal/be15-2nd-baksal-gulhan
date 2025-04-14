package com.hanhwa_tae.firstserver.like.command.application.dto.request;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LikeCreateRequest {
    @Min(1)
    private final int targetId;
    @NotBlank
    private final TargetType targetType;
}
