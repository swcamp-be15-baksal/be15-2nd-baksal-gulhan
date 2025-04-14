package com.hanhwa_tae.firstserver.packages.query.mapper;



import com.hanhwa_tae.firstserver.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.firstserver.packages.query.dto.response.PackageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PackageMapper {
    List<PackageDTO> selectPackages(PackageSearchRequest request);
    long countPackages(PackageSearchRequest request);
    PackageDTO selectPackageById(Integer packageId);
}