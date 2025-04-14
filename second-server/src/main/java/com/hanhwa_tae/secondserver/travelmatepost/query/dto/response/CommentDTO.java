package com.hanhwa_tae.secondserver.travelmatepost.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
