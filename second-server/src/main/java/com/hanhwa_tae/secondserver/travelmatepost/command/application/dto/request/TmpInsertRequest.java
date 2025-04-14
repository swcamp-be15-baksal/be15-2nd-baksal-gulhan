package com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class TmpInsertRequest {

    private String title;

    private String content;
}
