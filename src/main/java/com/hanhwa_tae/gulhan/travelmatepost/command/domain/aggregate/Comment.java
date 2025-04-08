package com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
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
}