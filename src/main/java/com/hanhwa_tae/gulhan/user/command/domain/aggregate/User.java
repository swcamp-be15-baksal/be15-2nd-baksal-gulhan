package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(unique = true)
    private String userId;

    private String password;

    private String userName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender; //'M','F'

    @JoinColumn(name = "rank_id")
    private int rankId = 1; // 기본 rankId =1 평민, FK
}