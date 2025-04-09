package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;


@Entity
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankId;
    @Enumerated(EnumType.STRING)
    private RankType rankName;
    private int pointRate; // 적립률
}