package com.hanhwa_tae.secondserver.travelmatepost.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmpSearchRequest {

    private Integer page = 1;
    private Integer size = 20;
    private String title;

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}
