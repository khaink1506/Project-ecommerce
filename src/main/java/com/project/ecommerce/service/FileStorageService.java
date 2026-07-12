package com.project.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveFile(MultipartFile multipartFile);

    void deleteFile(String fileName);
}
