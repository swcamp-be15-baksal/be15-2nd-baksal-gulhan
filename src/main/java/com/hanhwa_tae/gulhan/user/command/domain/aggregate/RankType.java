package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import lombok.Getter;

@Getter
public enum RankType {
    SLAVE("노비", 0),
    COMMONER("평민", 1),
    CHUNGIN("중인", 2),
    NOBLE("양반", 3),
    KING("왕", 4);

    private final String rankName;
    private final int pointRate;

    RankType(String rankName, int pointRate) {
        this.rankName = rankName;
        this.pointRate = pointRate;
    }
}
