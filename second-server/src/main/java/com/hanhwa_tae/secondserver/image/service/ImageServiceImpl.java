package com.hanhwa_tae.secondserver.image.service;


import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.image.dto.request.SaveImageRequest;
import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import com.hanhwa_tae.secondserver.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final FileUtil fileUtil;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final String CLOUD_FRONT_DOMAIN = "https://d152i3f1t56z95.cloudfront.net/";
    private final String SAVE_IMAGE_DIR = "image";
    private final S3Client s3Client;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile file) throws Exception {
        log.info("파일 위조 확인 : "+ fileUtil.validateFile(file));

        /* 이미지 mime 타입, 확장자 평가*/
        if(!fileUtil.validateFile(file)){
            throw new BusinessException(ErrorCode.IMAGE_FORMAT_ERROR);
        }

        /* 이미지 크기 평가 */
        if(file.getSize() > MAX_FILE_SIZE){
            throw new BusinessException(ErrorCode.IMAGE_TOO_BIG);
        }

        log.info("변경 이전 파일 이름" + file.getOriginalFilename());
        log.info("파일 타입 확인" + file.getContentType());

        String originalFilename = file.getOriginalFilename();
        String extension = fileUtil.getExtension(originalFilename);
        String uuidFilename = UUID.randomUUID() + "." + extension;
        String imageName = "temp/" + uuidFilename;

        log.info("변경된 파일 이름: " + uuidFilename);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(
                        file.getInputStream(), file.getSize()));

        String imageUrl = CLOUD_FRONT_DOMAIN + imageName;

        return ImageUploadResponse.builder()
                .imageUrl(imageUrl)
                .build();
    }

    @Override
    public void saveImage(SaveImageRequest request) {

        for (String imageUrl : request.getImageList()) {
            // 유효성 검증
            if (imageUrl == null || !imageUrl.startsWith(CLOUD_FRONT_DOMAIN)) {
                throw new BusinessException(ErrorCode.INVALID_IMAGE_URL);
            }

            String tempKey = imageUrl.replace(CLOUD_FRONT_DOMAIN, ""); // temp/uuid.jpg
            String uuidFileName = tempKey.substring("temp/".length()); // uuid.jpg
            String targetKey = SAVE_IMAGE_DIR + "/" + uuidFileName;

            try {
                // 1. 복사
//                s3Client.copyObject(builder -> builder
//                        .copySource(CopySource.builder()
//                                .bucket(bucketName)
//                                .key(tempKey)
//                                .build())
//                        .destinationBucket(bucketName)
//                        .destinationKey(targetKey)
//                        .build()
//                );

                s3Client.copyObject(
                        CopyObjectRequest.builder()
                                .sourceBucket(bucketName)
                                .sourceKey(tempKey)
                                .destinationBucket(bucketName)
                                .destinationKey(targetKey)
                                .build()
                );

                // 2. 삭제
                s3Client.deleteObject(builder -> builder
                        .bucket(bucketName)
                        .key(tempKey)
                        .build());

                log.info("S3 파일 이동 성공: {} → {}", tempKey, targetKey);
            } catch (Exception e) {
                log.error("S3 이미지 이동 실패: {} → {}, 에러: {}", tempKey, targetKey, e.getMessage(), e);
                throw new BusinessException(ErrorCode.S3_IMAGE_MOVE_FAIL);
            }
        }
    }
}
