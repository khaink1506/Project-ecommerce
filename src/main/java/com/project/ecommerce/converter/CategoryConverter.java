package com.project.ecommerce.converter;

import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.model.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ModelMapper modelMapper;

    public CategoryResponseDTO toCategoryResponseDTO(CategoryEntity category){
        return modelMapper.map(category, CategoryResponseDTO.class);
    }
}
