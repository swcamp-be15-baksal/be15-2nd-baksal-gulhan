package com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate;

import com.hanhwa_tae.secondserver.common.domain.DeleteType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE comment SET is_deleted = 'Y' WHERE comment_id = ?")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_mate_post_id")
    private TravelMatePost travelMatePostId; // FK

    @ManyToOne(fetch = FetchType.LAZY) // 부모 댓글 (대댓글을 포함한 댓글에 대한 참조)
    @JoinColumn(name = "parent_comment_id")  // 부모 댓글의 외래 키
    private Comment parentCommentId;


    public void updateComment(@NotBlank(message = "내용입력은 필수입니다.") String content) {
        this.content = content;
    }
}