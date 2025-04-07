package com.hanhwa_tae.gulhan.packages.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageListResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageResponse;
import com.hanhwa_tae.gulhan.packages.query.mapper.PackageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageQueryService {
    private final PackageMapper packageMapper;

    public PackageResponse getPackageDetail(int packageId) {
        return packageMapper.selectPackageById(packageId);
    }

    public PackageListResponse getPackageList(PackageSearchRequest packageSearchRequest) {
        List<PackageResponse> packageList = packageMapper.selectPackages(packageSearchRequest);
        long totalPackages = packageMapper.countPackages(packageSearchRequest);  // 전체 패키지 수

        Pagination pagination = Pagination.builder()
                .currentPage(packageSearchRequest.getPage())
                .totalSize((int)Math.ceil((double) totalPackages / packageSearchRequest.getSize()))
                .totalPosts(totalPackages)
                .build();

        return PackageListResponse.builder()
                .packageList(packageList)
                .pagination(pagination)
                .build();
    }
}
