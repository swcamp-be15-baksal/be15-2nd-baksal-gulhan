package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import jakarta.annotation.Nullable;
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

    private Integer parentCommentId;

    private List<CommentDTO> children;
}
