package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -84243949359L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false )
    String name;

    @Column(name = "code", unique = true, nullable = false)
    String code;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();
}
