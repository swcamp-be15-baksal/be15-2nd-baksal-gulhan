package com.hanhwa_tae.gulhan.like.command.application.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeCommandResponse {
    private boolean liked;
    private String message;
    private int targetId;
    private TargetType targetType;
}
