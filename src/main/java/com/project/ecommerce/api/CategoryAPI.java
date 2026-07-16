package com.project.ecommerce.api;

import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;
import com.project.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryAPI {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        return ResponseEntity.ok().body(categoryService.createCategory(categoryRequestDTO));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO){
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryRequestDTO));
    }


    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable List<Long> ids){
        return ResponseEntity.ok().body(categoryService.deleteCategory(ids));
    }

    @DeleteMapping("/soft/{ids}")
    public ResponseEntity<ResponseDTO> softDeleteCategory(@PathVariable List<Long> ids){
        return ResponseEntity.ok().body(categoryService.softDeleteCategory(ids));
    }
}
