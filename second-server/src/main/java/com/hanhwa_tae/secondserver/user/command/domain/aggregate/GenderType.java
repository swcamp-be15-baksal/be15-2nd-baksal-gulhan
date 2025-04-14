package com.hanhwa_tae.secondserver.user.command.domain.aggregate;

import lombok.Getter;

@Getter
public enum GenderType {
    M("남자"),
    F("여자");

    private final String gender;

    GenderType(String gender) {
        this.gender = gender;
    }
}
