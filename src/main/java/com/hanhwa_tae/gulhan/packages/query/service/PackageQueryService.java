package com.hanhwa_tae.gulhan.packages.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.common.exception.custom.PageNotFoundException;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageListResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageDTO;
import com.hanhwa_tae.gulhan.packages.query.mapper.PackageMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageQueryService {
    private final PackageMapper packageMapper;

    @Transactional(readOnly=true)
    public PackageListResponse getPackages(PackageSearchRequest packageSearchRequest) {
        packageSearchRequest.calculatePaging();
        List<PackageDTO> packages = packageMapper.selectPackages(packageSearchRequest);
        long totalPackages = packageMapper.countPackages(packageSearchRequest);  // 전체 패키지 수

        int page = packageSearchRequest.getPage();
        int size = packageSearchRequest.getSize();

        return PackageListResponse.builder()
                .packages(packages)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int)Math.ceil((double)totalPackages/size))
                        .size(size)
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public PackageDTO getPackageById(Integer packageId) {
        PackageDTO dto = packageMapper.selectPackageById(packageId);
        if (dto != null) {
            dto.setRemaining(dto.getCapacity() - dto.getCurrentRegist());
        } else {
            throw new PageNotFoundException(ErrorCode.PAGE_NOT_FOUND);
        }
        dto.setAvgRating(dto.getAvgRating());
        return dto;
    }
}

