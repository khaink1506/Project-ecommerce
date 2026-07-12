package com.project.ecommerce.converter;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.model.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final ModelMapper modelMapper;

    public ProductEntity toProductEntity(ProductRequestDTO productRequestDTO){
        return modelMapper.map(productRequestDTO, ProductEntity.class);
    }

    public ProductResponseDTO toProductResponseDTO(ProductEntity productEntity){
        ProductResponseDTO productResponseDTO = modelMapper.map(productEntity, ProductResponseDTO.class);
        productResponseDTO.setCategoryId(productEntity.getCategory().getId());
        productResponseDTO.setCategoryName(productEntity.getCategory().getName());

        return productResponseDTO;
    }
}
