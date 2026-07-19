package com.project.ecommerce.service.impl;

import com.project.ecommerce.model.request.CategoryRequestDTO;
import com.project.ecommerce.model.response.CategoryResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public ResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        return null;
    }

    @Override
    public ResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO) {
        return null;
    }

    @Override
    public ResponseDTO deleteCategory(List<Long> ids) {
        return null;
    }

    @Override
    public ResponseDTO softDeleteCategory(List<Long> ids) {
        return null;
    }
}
