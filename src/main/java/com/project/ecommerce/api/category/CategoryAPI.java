package com.project.ecommerce.api.category;

import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.CategoryRequestDTO;
import com.project.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorys")
@RequiredArgsConstructor
public class CategoryAPI {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO,
                                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Dữ liệu không hợp lệ");
            List<String> details =bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
            responseDTO.setDetails(details);
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(categoryService.createCategory(categoryRequestDTO));
    }
}
