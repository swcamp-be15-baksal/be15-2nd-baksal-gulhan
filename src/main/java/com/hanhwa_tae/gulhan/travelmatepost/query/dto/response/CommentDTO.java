package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class CommentDTO {

    private int commentId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int userNo;

    private String userId;

    private int travelMatePostId;

    private int parentCommentId;

    private List<CommentDTO> children;
}
