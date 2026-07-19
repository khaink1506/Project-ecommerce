package com.project.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_specifications", indexes = {
        @Index(name = "idx_spec_product", columnList = "product_id"),
        @Index(name = "idx_spec_key", columnList = "specification_key")})
@Getter
@Setter
public class ProductSpecificationEntity extends BaseEntity {

    /**
     * Sản phẩm sở hữu specification
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    /**
     * Tên thuộc tính
     * <p>
     * VD:
     * RAM
     * CPU
     * COLOR
     */
    @Column(name = "specification_key", nullable = false, length = 100)
    private String specificationKey;

    /**
     * Giá trị thuộc tính
     * <p>
     * VD:
     * 16GB
     * Intel i7
     */
    @Column(name = "specification_value", nullable = false, length = 255)
    private String specificationValue;

    /**
     * Thứ tự hiển thị
     */
    @Column(name = "sort_order")
    private Integer sortOrder = 0;


}