package com.hanhwa_tae.secondserver.utils;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileUtil {

    private static final Tika tika = new Tika();
    private static final Set<String> ALLOWED_MIME_TYPES = new HashSet<>(Arrays.asList(
            "image/jpeg",
            "image/png"
    ));

    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
            "jpg",
            "jpeg",
            "png"
    ));

    public boolean validateFile(MultipartFile file) throws Exception {
        String mimeType = tika.detect(file.getInputStream());
        String extension = getExtension(file.getOriginalFilename());

        return ALLOWED_MIME_TYPES.contains(mimeType) && ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }

    // 확장자 추출
    public String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
