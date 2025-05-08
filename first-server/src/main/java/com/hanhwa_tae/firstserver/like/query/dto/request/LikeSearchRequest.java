package com.hanhwa_tae.firstserver.like.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeSearchRequest {
    private Integer page = 1;
    private Integer size = 10;

    private String targetType = null;

    public int getOffset() { return (page - 1) * size; }

    public int getLimit() { return size; }
}
