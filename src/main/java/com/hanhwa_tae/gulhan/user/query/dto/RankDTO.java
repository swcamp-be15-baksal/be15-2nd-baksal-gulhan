package com.hanhwa_tae.gulhan.user.query.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RankDTO {
    @Setter
    private String rankName;

    private int pointRate;

}
