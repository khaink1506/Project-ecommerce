package com.project.ecommerce.api.product;

import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO){
       return ResponseEntity.ok().body(productService.createProduct(productRequestDTO));
    }
}
