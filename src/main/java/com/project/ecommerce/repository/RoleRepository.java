package com.project.ecommerce.repository;

import com.project.ecommerce.entity.RoleEntity;
import com.project.ecommerce.enums.RoleCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByCode(RoleCode code);
}
