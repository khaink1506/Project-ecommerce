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
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryRequestDTO.getName());
        categoryEntity.setStatus(true);
        categoryRepository.save(categoryEntity);
        return ResponseDTO.builder()
                .message("Thêm danh mục thành công")
                .build();
    }
}
