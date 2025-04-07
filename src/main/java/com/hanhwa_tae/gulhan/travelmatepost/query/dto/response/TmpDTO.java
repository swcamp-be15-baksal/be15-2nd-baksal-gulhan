package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class TmpDTO {

    private int travelMatePostId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int userNo;

    private String userId;

}
