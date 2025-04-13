package com.hanhwa_tae.gulhan.user.query.dto.response;

import com.hanhwa_tae.gulhan.user.query.dto.RankDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class RankInfoResponse {

    private List<RankDTO> rankList;
}
