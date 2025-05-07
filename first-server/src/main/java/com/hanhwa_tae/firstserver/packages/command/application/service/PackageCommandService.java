package com.hanhwa_tae.firstserver.packages.command.application.service;

import com.hanhwa_tae.firstserver.client.SaveImageRequest;
import com.hanhwa_tae.firstserver.client.UserClient;
import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.packages.command.domain.aggregate.Packages;
import com.hanhwa_tae.firstserver.packages.command.domain.repository.JpaPackageRepository;
import com.hanhwa_tae.firstserver.packages.query.dto.request.PackageInsertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageCommandService {
    private final ModelMapper modelMapper;
    private final JpaPackageRepository jpaPackageRepository;
    private final UserClient userClient;

    @Transactional
    public int insertPackage(PackageInsertRequest request) {
        Packages packages = modelMapper.map(request, Packages.class);

        List<String> imageUrls = request.getImageUrls();

        if (!imageUrls.isEmpty()) {
            packages.setFirstImage(imageUrls.get(0).replace("/temp/", "/image/"));
            userClient.saveImage(SaveImageRequest.builder()
                    .imageList(imageUrls)
                    .build());
        }
        log.info("메인 이미지 나와라~ : {}",packages.getFirstImage());

        Packages newPackages = jpaPackageRepository.save(packages);

        return newPackages.getPackageId();
    }

    @Transactional
    public void updatePackage(Integer packageId, PackageInsertRequest request) {
        Packages packages = jpaPackageRepository.findById(packageId).orElseThrow();

        List<String> imageUrls = request.getImageUrls();

        if (!imageUrls.isEmpty()) {
            userClient.saveImage(SaveImageRequest.builder()
                    .imageList(imageUrls)
                    .build());
        }

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
                request.getGuidePhone(),
                request.getRemaining(),
                imageUrls.get(0).replace("/temp/", "/image/")
        );
    }

    @Transactional
    public void deletePackage(Integer packageId) {
        Packages packages = jpaPackageRepository.findById(packageId).orElseThrow();
        packages.setIsDeleted(DeleteType.Y);
    }
}

