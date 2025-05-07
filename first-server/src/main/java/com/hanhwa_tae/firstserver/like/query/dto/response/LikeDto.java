package com.hanhwa_tae.firstserver.like.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDto {
    private String targetId;
    private String targetType;
    private String targetName;
    private String userId;
}
