package com.hanhwa_tae.secondserver.notice.command.domain.aggregate;

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
@Table(name = "notice")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE notice SET is_deleted = 'Y' WHERE notice_id = ?")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user; // FK

    public void updateNotice(@NotBlank(message = "제목입력필수") String title, @NotBlank(message = "내용입력필수") String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

}