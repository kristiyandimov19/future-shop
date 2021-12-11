package com.example.futureshop.services.impl;

import com.cloudinary.Cloudinary;
import com.example.futureshop.services.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;
    private static final String TMP_FILE = "tmp-file";
    private static final String URL = "url";

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File tmpFile = File.createTempFile(TMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tmpFile);

        return cloudinary.uploader()
                .upload(tmpFile, Collections.emptyMap())
                .get(URL).toString();
    }
}
