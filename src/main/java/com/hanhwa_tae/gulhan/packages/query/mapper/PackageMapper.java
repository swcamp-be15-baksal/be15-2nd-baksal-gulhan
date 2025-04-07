package com.hanhwa_tae.gulhan.packages.query.mapper;


import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PackageMapper {
    List<PackageResponse> selectPackages(PackageSearchRequest request);
    long countPackages(PackageSearchRequest request);

    PackageResponse selectPackageById(int packageId);
}