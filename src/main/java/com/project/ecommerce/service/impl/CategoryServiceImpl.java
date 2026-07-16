package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.CategoryConverter;
import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.response.CategoryResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryConverter::toCategoryResponseDTO)
                .toList();
    }
    @Override
    @Transactional
    public ResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.existsByName(categoryRequestDTO.getName().trim())) {
            throw new InvalidRequestException("Danh mục đã tồn tại");
        }
        CategoryEntity category = CategoryEntity.builder()
                .name(categoryRequestDTO.getName().trim())
                .description(categoryRequestDTO.getDescription())
                .isDeleted(false)
                .isActivated(true)
                .build();

        categoryRepository.save(category);
        return ResponseDTO.builder()
                .data(category)
                .message("Thêm danh mục thành công")
                .build();
    }
    @Override
    @Transactional
    public ResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO.getId() == null) {
            throw new InvalidRequestException("Có ID mới cập nhật danh mục");
        }
        CategoryEntity existingCategory = categoryRepository.findById(categoryRequestDTO.getId())
                .orElseThrow(() -> new InvalidRequestException("ID không tồn tại"));
        existingCategory.setName(categoryRequestDTO.getName());
        existingCategory.setDescription(categoryRequestDTO.getDescription());
        return ResponseDTO.builder()
                .data(existingCategory)
                .message("Sửa dữ liệu danh mục thành công")
                .build();
    }
    @Override
    @Transactional
    public ResponseDTO deleteCategory(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw new InvalidRequestException("ID không tồn tại");
        }
        List<CategoryEntity> category = categoryRepository.findAllById(ids);
        if (category.size() != ids.size()) {
            throw new InvalidRequestException("ID danh mục không tồn tại");
        }
        categoryRepository.deleteAll(category);
        return ResponseDTO.builder()
                .data(category)
                .message("Xóa danh mục thành công")
                .build();
    }
    @Override
    @Transactional
    public ResponseDTO softDeleteCategory(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw new InvalidRequestException("ID không tồn tại");
        }
        List<CategoryEntity> category = categoryRepository.findAllById(ids);
        if (category.size() != ids.size()) {
            throw new InvalidRequestException("ID danh mục không tồn tại");
        }
        category.forEach(field -> field.setDeleted(true));
        categoryRepository.saveAll(category);
        return ResponseDTO.builder()
                .data(category)
                .message("Xóa mềm thành công")
                .build();
    }
}
