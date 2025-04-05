package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankId;

    private RankType rankName;

    private Long pointRate; // 적립률


    @OneToMany(mappedBy = "rank")
    private List<User> users;
}