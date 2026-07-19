package com.project.ecommerce.service.impl;

import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.model.response.ProductResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public ResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public ResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public ResponseDTO deleteProduct(List<Long> ids) {
        return null;
    }

    @Override
    public ResponseDTO softDeleteProduct(List<Long> ids) {
        return null;
    }
}
