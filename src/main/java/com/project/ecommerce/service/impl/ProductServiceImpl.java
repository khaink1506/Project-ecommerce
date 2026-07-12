package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.ProductConverter;
import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;
    @Override
    @Transactional
    public ResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        if(productRepository.existsByName(productRequestDTO.getName())){
            throw new InvalidRequestException("Tên sản phẩm đã tồn tại");
        }
        CategoryEntity category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new InvalidRequestException("ID danh mục không tồn tại"));

        ProductEntity productEntity = productConverter.toProductEntity(productRequestDTO);
        productEntity.setCategory(category);
        productEntity.setDeleted(false);
        productEntity.setActivated(true);
        productRepository.save(productEntity);
        return ResponseDTO.builder()
                .message("Thêm sản phẩm thành công")
                .data(productConverter.toProductResponseDTO(productEntity))
                .build();
    }
}
