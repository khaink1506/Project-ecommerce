package com.project.ecommerce.api.brand;

import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.BrandRequestDTO;
import com.project.ecommerce.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandAPI {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createBrand(@RequestBody @Valid BrandRequestDTO brandRequestDTO,
                                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Dữ liệu không hợp lệ");
            List<String> details = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
            responseDTO.setDetails(details);
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(brandService.createBrand(brandRequestDTO));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateBrand(@RequestBody @Valid BrandRequestDTO brandRequestDTO,
                                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Dữ liệu không hợp");
            List<String> details = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
            responseDTO.setDetails(details);
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(brandService.updateBrand(brandRequestDTO));
    }
}
