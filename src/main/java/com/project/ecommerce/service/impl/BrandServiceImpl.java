package com.project.ecommerce.service.impl;

import com.project.ecommerce.entity.BrandEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.dto.ResponseDTO;
import com.project.ecommerce.model.request.BrandRequestDTO;
import com.project.ecommerce.repository.BrandRepository;
import com.project.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    @Override
    public ResponseDTO createBrand(BrandRequestDTO brandRequestDTO) {
        if(brandRepository.existsByName(brandRequestDTO.getName())){
            throw new InvalidRequestException("Hãng đã tồn tại");
        }
        BrandEntity brandEntity = BrandEntity.builder()
                .name(brandRequestDTO.getName())
                .status(true)
                .build();
        brandRepository.save(brandEntity);
        return ResponseDTO.builder()
                .message("Thêm hãng thành công")
                .build();
    }

    @Override
    public ResponseDTO updateBrand(BrandRequestDTO brandRequestDTO) {
        if(brandRequestDTO.getId() == null){
            throw new InvalidRequestException("Phải có id mới được cập nhật");
        }
        BrandEntity brandEntity = brandRepository.findById(brandRequestDTO.getId())
                .orElseThrow(() -> new InvalidRequestException("id không tồn tại"));
        brandEntity.setName(brandRequestDTO.getName());
        brandEntity.setStatus(true);
        brandRepository.save(brandEntity);
        return ResponseDTO.builder()
                .data(brandEntity)
                .message("Cập nhật thành công")
                .build();
    }
}
