package com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request;

import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CommentInsertRequest {

    private Integer parentCommentId;
    @NotBlank(message = "댓글 내용 필수 입력")
    private String content;

    @NotBlank(message = "회원 번호는 필수입니다.")
    private int userNo;
}
