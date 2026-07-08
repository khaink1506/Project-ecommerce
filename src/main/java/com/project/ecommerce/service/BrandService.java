package com.project.ecommerce.service;

import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.BrandRequestDTO;

public interface BrandService {

    ResponseDTO createBrand(BrandRequestDTO brandRequestDTO);

    ResponseDTO updateBrand(BrandRequestDTO brandRequestDTO);
}
