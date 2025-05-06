package com.hanhwa_tae.secondserver.user.query.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserBoardResponse {

    private List<UserBoardDTO> BoardList;
}
