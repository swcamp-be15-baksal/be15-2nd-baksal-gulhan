package com.hanhwa_tae.firstserver.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankId;
    @Enumerated(EnumType.STRING)
    private RankType rankName;
    private int pointRate; // 적립률

}