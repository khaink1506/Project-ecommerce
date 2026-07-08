package com.project.ecommerce.repository;

import com.project.ecommerce.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
    List<CategoryEntity> findAllByStatusTrue();
}
