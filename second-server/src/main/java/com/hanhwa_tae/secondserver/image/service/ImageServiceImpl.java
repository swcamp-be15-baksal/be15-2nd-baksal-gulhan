package com.hanhwa_tae.secondserver.image.service;


import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.image.dto.response.ImageUploadResponse;
import com.hanhwa_tae.secondserver.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
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
}
