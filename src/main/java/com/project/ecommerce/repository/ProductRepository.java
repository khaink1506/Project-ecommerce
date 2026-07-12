package com.project.ecommerce.repository;

import com.project.ecommerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    List<ProductEntity> findByIsDeletedFalse();
}
