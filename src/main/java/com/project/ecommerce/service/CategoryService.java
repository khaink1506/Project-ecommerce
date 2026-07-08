package com.project.ecommerce.service;

import com.project.ecommerce.model.dto.CategoryResponseDTO;
import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAll();

    ResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO);
}
