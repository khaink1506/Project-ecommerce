package com.project.ecommerce.service;

import com.project.ecommerce.model.response.ProductResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAll();

    ResponseDTO updateProduct(ProductRequestDTO productRequestDTO);

    ResponseDTO deleteProduct(List<Long> ids);

    ResponseDTO softDeleteProduct(List<Long> ids);
}
