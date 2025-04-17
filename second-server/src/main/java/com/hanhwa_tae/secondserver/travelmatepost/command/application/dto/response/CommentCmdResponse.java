package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentCmdResponse {
    private int commentId;

    private Integer parentCommentId;

    private int travelMatePostId;

    private String content;

    private String userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
