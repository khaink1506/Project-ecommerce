package com.project.ecommerce.service.impl;
import com.project.ecommerce.config.FileStorageProperties;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageProperties properties;

    @Override
    public String saveFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        try {
            // Đường dẫn thư mục upload
            Path uploadPath = Paths.get(properties.getUploadDir());
            // Nếu thư mục chưa tồn tại thì tạo mới
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // Lấy tên file gốc
            String originalFileName = multipartFile.getOriginalFilename();
            // Sinh tên file mới để tránh trùng
            String fileName = UUID.randomUUID() + "_" + originalFileName;
            // Đường dẫn đầy đủ của file
            Path destination = uploadPath.resolve(fileName);
            // Copy file vào thư mục uploads
            Files.copy(multipartFile.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new InvalidRequestException("Upload ảnh thất bại");
        }
    }
    @Override
    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return;
        }
        try {
            Path filePath = Paths.get(properties.getUploadDir()).resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new InvalidRequestException("Xóa ảnh thất bại");
        }
    }
}