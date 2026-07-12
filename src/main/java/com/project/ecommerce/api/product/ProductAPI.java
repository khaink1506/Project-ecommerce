package com.project.ecommerce.api.product;

import com.project.ecommerce.model.response.ProductResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        return productService.getAll();
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> createProduct(@ModelAttribute @Valid ProductRequestDTO productRequestDTO){
       return ResponseEntity.ok().body(productService.createProduct(productRequestDTO));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute @Valid ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok().body(productService.updateProduct(productRequestDTO));
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable List<Long> ids){
        return ResponseEntity.ok().body(productService.deleteProduct(ids));
    }

    @DeleteMapping("/soft/{ids}")
    public ResponseEntity<ResponseDTO> softDeleteProduct(@PathVariable  List <Long> ids){
        return ResponseEntity.ok().body(productService.softDeleteProduct(ids));
    }
}
