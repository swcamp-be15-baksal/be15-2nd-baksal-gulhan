package com.hanhwa_tae.secondserver.user.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RankDTO {
    @Setter
    private String rankName;

    private int pointRate;

}
