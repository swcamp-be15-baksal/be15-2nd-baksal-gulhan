package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

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

}
