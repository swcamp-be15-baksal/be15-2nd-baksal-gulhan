package com.hanhwa_tae.gulhan.common.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pagination {
    private final int currentPage;
    private final int totalSize;
    private final long totalPosts;
}
