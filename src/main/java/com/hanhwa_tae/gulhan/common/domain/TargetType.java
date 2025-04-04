package com.hanhwa_tae.gulhan.common.domain;

import lombok.Getter;

@Getter
public enum TargetType {
    PACKAGE("패키지"),
    GOODS("기념품"),
    PLACE("장소");

    private final String name;
    TargetType(String name) {
        this.name = name;
    }
}
