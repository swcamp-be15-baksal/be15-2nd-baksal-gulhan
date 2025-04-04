package com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "travel_mate_post")
public class TravelMatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int travelMatePostId;

    private String title;

    @Lob
    private String content;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;
// user 엔터티 추가되면 주석 제거
//    @ManyToOne
//    @JoinColumn(name = "userNo", nullable = false)
//    private User user;
}