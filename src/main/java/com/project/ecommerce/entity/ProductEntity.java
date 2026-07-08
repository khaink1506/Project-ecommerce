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
@Table(name = "product")
public class ProductEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -81246246424L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "sku", nullable = false, unique = true)
    String sku;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Column(name = "sold_quantity")
    Integer soldQuantity = 0;

    @Column(name = "thumbnail")
    String thumbnail;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "status", nullable = false)
    Boolean status = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    BrandEntity brand;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();
}
