package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "products", indexes = {
        // Tìm kiếm theo category
        @Index(name = "idx_product_category", columnList = "category_id"),
        // Tìm kiếm theo brand
        @Index(name = "idx_product_brand", columnList = "brand_id"),
        // Lọc sản phẩm active + sắp xếp mới nhất
        @Index(name = "idx_product_active_created", columnList = "active, created_at"),
        // Lọc theo khoảng giá
        @Index(name = "idx_product_price", columnList = "price"),
        // Sắp xếp theo rating
        @Index(name = "idx_product_rating", columnList = "average_rating")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity {

    @Column(name = "sku", nullable = false, unique = true, length = 100)
    private String sku;

    @Column(name = "barcode", unique = true, length = 100)
    private String barcode;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "slug", nullable = false, unique = true, length = 255)
    private String slug;

    @Column(name = "thumbnail", length = 500)
    private String thumbnail;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Giá gốc
     */
    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    /**
     * Giá sau giảm
     */
    @Column(name = "discount_price", precision = 18, scale = 2)
    private BigDecimal discountPrice;

    /**
     * Tồn kho
     */
    @Column(nullable = false)
    private Integer quantity = 0;

    /**
     * Số lượng đang được giữ
     * Ví dụ: user checkout nhưng chưa thanh toán
     */
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity = 0;

    /**
     * Đã bán
     */
    @Column(nullable = false)
    private Integer sold = 0;

    private Integer viewCount = 0;

    @Column(precision = 2, scale = 1)
    private BigDecimal averageRating = BigDecimal.ZERO;

    private Integer totalReviews = 0;

    private Integer warrantyMonth = 12;

    private String origin;

    private BigDecimal weight;

    @Column(nullable = false)
    private Boolean active = true;

    /*
     * Category reference
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    /*
     * Brand reference
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> details = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<ProductSpecificationEntity> specifications = new ArrayList<>();

}

