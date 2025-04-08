package com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request;

import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    private int commentId;
    private String content;
    private int userNo;
}
