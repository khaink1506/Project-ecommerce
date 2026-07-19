package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands", indexes = {
                @Index(
                        name = "idx_brand_name",
                        columnList = "name"),
                @Index(
                        name = "idx_brand_slug",
                        columnList = "slug")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "slug", nullable = false, unique = true, length = 150)
    private String slug;

    @Column(name = "logo", length = 500)
    private String logo;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    /**
     * Một brand có nhiều product
     * Không cascade
     * Brand không sở hữu vòng đời Product
     */
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();


}
