package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import lombok.Getter;

@Getter
public enum Gender {
    M("남자"),
    F("여자");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
