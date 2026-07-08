package com.project.ecommerce.repository;

import com.project.ecommerce.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String name);
}
