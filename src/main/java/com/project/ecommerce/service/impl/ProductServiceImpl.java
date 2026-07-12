package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.ProductConverter;
import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.response.ProductResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.request.ProductRequestDTO;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.service.FileStorageService;
import com.project.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;
    private final FileStorageService fileStorageService;
    @Override
    @Transactional
    public ResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        if(productRepository.existsByName(productRequestDTO.getName())){
            throw new InvalidRequestException("Tên sản phẩm đã tồn tại");
        }
        CategoryEntity category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new InvalidRequestException("ID danh mục không tồn tại"));

        ProductEntity productEntity = productConverter.toProductEntity(productRequestDTO);
        productEntity.setCategory(category);
        productEntity.setDeleted(false);
        productEntity.setActivated(true);
        productEntity.setImage(fileStorageService.saveFile(productRequestDTO.getImage()));
        productRepository.save(productEntity);
        return ResponseDTO.builder()
                .message("Thêm sản phẩm thành công")
                .data(productConverter.toProductResponseDTO(productEntity))
                .build();
    }
    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findByIsDeletedFalse()
                .stream()
                .map(productConverter::toProductResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
        if(productRequestDTO.getId() == null){
            throw new InvalidRequestException("Có ID mới cập nhật được sản phẩm");
        }
        ProductEntity productEntity = productRepository.findById(productRequestDTO.getId())
                .orElseThrow(() -> new InvalidRequestException("ID không tồn tại"));
        CategoryEntity categoryEntity = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new InvalidRequestException("Danh mục không tồn tại"));
        if(productRepository.existsByName(productRequestDTO.getName())){
            throw new InvalidRequestException("Tên sản phẩm đã tồn tại");
        }
        productConverter.updateProductEntity(productRequestDTO, productEntity);
        productEntity.setCategory(categoryEntity);
        if(productRequestDTO.getImage() != null && !productRequestDTO.getImage().isEmpty()){
            fileStorageService.deleteFile(productEntity.getImage());
            productEntity.setImage(fileStorageService.saveFile(productRequestDTO.getImage()));
        }
        productRepository.save(productEntity);
        return ResponseDTO.builder()
                .message("Cập nhật sản phẩm thành công")
                .data(productConverter.toProductResponseDTO(productEntity))
                .build();
    }

    @Override
    public ResponseDTO deleteProduct(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw new InvalidRequestException("Danh sách ID không tồn tại");
        }
        List<ProductEntity> productEntity = productRepository.findAllById(ids);
        if(productEntity.size() != ids.size()){
            throw new InvalidRequestException("Sản phẩm không tồn tại");
        }
        productEntity.forEach(product -> fileStorageService.deleteFile(product.getImage()));
        productRepository.deleteAll(productEntity);
        return ResponseDTO.builder()
                .message("Xóa sản phẩm thành công")
                .build();
    }

    @Override
    public ResponseDTO softDeleteProduct(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            throw new InvalidRequestException("Danh sách ID không tồn tại");
        }
        List<ProductEntity> productEntity = productRepository.findAllById(ids);
        if(productEntity.size() != ids.size()){
            throw new InvalidRequestException("Sản phẩm không tồn tại");
        }
        productEntity.forEach(product -> product.setDeleted(true));
        productRepository.saveAll(productEntity);
        return ResponseDTO.builder()
                .message("Xóa mềm sản phẩm thành công")
                .build();
    }
}
