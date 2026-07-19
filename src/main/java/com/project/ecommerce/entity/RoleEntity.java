package com.project.ecommerce.entity;

import com.project.ecommerce.enums.RoleCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles", indexes = {@Index(name = "idx_role_code", columnList = "code")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false, length = 100)
    private RoleCode code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Một role có nhiều user
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserEntity> users;


}
