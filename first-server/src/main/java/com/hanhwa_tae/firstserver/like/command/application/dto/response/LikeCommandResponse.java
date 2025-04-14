package com.hanhwa_tae.firstserver.like.command.application.dto.response;


import com.hanhwa_tae.firstserver.common.domain.TargetType;
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
