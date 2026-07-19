package com.project.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "product_images",
        indexes = {
                @Index(
                        name = "idx_product_image_product",
                        columnList = "product_id"
                )
        }
)
@Getter
@Setter
public class ProductImageEntity extends BaseEntity {

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "alt_text", length = 255)
    private String altText;



    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "is_thumbnail")
    private Boolean thumbnail = false;

    /**
     * Image thuộc Product nào
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


}