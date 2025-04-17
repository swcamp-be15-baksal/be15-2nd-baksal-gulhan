package com.hanhwa_tae.firstserver.packages.command.application.service;

import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.packages.command.domain.aggregate.Packages;
import com.hanhwa_tae.firstserver.packages.command.domain.repository.JpaPackageRepository;
import com.hanhwa_tae.firstserver.packages.query.dto.request.PackageInsertRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PackageCommandService {
    private final ModelMapper modelMapper;
    private final JpaPackageRepository jpaPackageRepository;

    @Transactional
    public int insertPackage(PackageInsertRequest request) {
        Packages packages = modelMapper.map(request, Packages.class);
        Packages newPackages = jpaPackageRepository.save(packages);

        return newPackages.getPackageId();
    }

    @Transactional
    public void updatePackage(Integer packageId, PackageInsertRequest request) {
        Packages packages = jpaPackageRepository.findById(packageId).orElseThrow();
        packages.updatePackage(
                request.getTitle(),
                request.getPrice(),
                request.getDetail(),
                request.getCapacity(),
                request.getArea(),
                request.getStartDate(),
                request.getEndDate(),
                request.getGuideName(),
                request.getGuideEmail(),
                request.getGuideGender(),
                request.getGuidePhone()
        );
    }

    @Transactional
    public void deletePackage(Integer packageId) {
        Packages packages = jpaPackageRepository.findById(packageId).orElseThrow();
        packages.setIsDeleted(DeleteType.Y);
    }
}

