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
@Table(name = "travel_mate_post")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@SQLDelete(sql = "UPDATE travel_mate_post SET is_deleted = 'Y' WHERE travel_mate_post_id = ?")
public class TravelMatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int travelMatePostId;

    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;


    public void updateProductDetails(@NotBlank(message = "제목을 입력하세요") String title, @NotBlank(message = "내용을 입력하세요.") String content) {
        if(title != null) this.title = title;
        if(content != null) this.content = content;
    }
}