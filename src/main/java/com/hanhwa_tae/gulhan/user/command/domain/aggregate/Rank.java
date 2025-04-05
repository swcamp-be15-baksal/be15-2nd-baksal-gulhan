package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankId;
    @Enumerated(EnumType.STRING)
    private RankName rankName;
    private int pointRate; // 적립률


    @OneToMany(mappedBy = "rank")
    private List<User> users;
}