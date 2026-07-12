package com.project.ecommerce.service;

import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;

public interface ProductService {

    ResponseDTO createProduct(ProductRequestDTO productRequestDTO);
}
