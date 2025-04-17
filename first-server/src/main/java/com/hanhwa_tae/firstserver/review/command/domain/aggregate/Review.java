package com.hanhwa_tae.firstserver.review.command.domain.aggregate;


import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private int targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; //enum : 'PACKAGE','GOODS'

    @Lob
    private String detail;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating; // decimal (2,1)

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // enum

    private Long userNo; // FK

    public void updateReview(@NotBlank String detail, @NotBlank BigDecimal rating) {
        this.detail = detail;
        this.rating = rating;
    }
}