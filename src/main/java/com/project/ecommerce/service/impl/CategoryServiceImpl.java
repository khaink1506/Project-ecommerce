package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.CategoryConverter;
import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.dto.CategoryResponseDTO;
import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAllByStatusTrue()
                .stream()
                .map(categoryConverter::toCategoryResponseDTO)
                .toList();

    }

    @Override
    @Transactional
    public ResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        if(categoryRepository.existsByName(categoryRequestDTO.getName())){
            throw new InvalidRequestException("Tên danh mục đã tồn tại");
        }
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(categoryRequestDTO.getName())
                .status(true)
                .build();

        categoryRepository.save(categoryEntity);
        return ResponseDTO.builder()
                .message("Thêm danh mục thành công")
                .build();
    }

    @Override
    public ResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO) {
        if(categoryRequestDTO.getId() == null){
            throw new InvalidRequestException("Phải có id để cập nhật danh mục");
        }
        CategoryEntity categoryEntity = categoryRepository.findById(categoryRequestDTO.getId())
                .orElseThrow(() -> new InvalidRequestException("Id không tồn tại"));
        categoryEntity.setName(categoryRequestDTO.getName());
        categoryEntity.setStatus(true);
        categoryEntity.setModifiedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        return ResponseDTO.builder()
                .message("Cập nhật danh mục thành công")
                .build();
    }
}
