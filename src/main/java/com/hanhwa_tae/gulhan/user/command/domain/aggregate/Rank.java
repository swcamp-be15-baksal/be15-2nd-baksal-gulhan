package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private int rankId;
    @Enumerated(EnumType.STRING)
    private RankType rankName;
    private int pointRate; // 적립률
=======
    private Long rankId;

    @Enumerated(EnumType.STRING)
    private RankType rankName;

    private Long pointRate; // 적립률

    @OneToMany(mappedBy = "rank")
    private List<User> users;
>>>>>>> cd430ab (Feat: kakao 로그인/회원가입 기능 개발 #8)
}