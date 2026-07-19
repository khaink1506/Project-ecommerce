package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tags", indexes = {

        @Index(name = "idx_tag_name", columnList = "name"),

        @Index(name = "idx_tag_slug", columnList = "slug")})
@Getter
@Setter
public class TagEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String slug;

    /**
     * Many-to-many với Product
     */
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<ProductEntity> product = new HashSet<>();
}