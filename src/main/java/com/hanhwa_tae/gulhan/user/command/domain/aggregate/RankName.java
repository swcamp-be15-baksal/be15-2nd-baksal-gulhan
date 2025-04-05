package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import lombok.Getter;

@Getter
public enum RankName {
    COMMONER("평민"),
    CHUNGIN("중인"),
    NOBLE("양반"),
    KING("왕"),
    SLAVE("노비");

    private final String rankName;

    RankName(String rankName) {
        this.rankName = rankName;
    }
}
