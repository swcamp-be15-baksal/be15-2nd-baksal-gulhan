package com.hanhwa_tae.gulhan.notice.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import org.springframework.data.annotation.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;
    @Lob
    private String content;
    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // FK
}