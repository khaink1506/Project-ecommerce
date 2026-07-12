package com.project.ecommerce.service;

import com.project.ecommerce.model.response.CategoryResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAll();

    ResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseDTO deleteCategory(List<Long> ids);

    ResponseDTO softDeleteCategory(List<Long> ids);

}
