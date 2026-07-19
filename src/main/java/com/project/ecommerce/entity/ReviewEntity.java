package com.project.ecommerce.entity;

import com.project.ecommerce.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "reviews", indexes = {

        @Index(name = "idx_review_product", columnList = "product_id"),

        @Index(name = "idx_review_user", columnList = "user_id"),

        @Index(name = "idx_review_status", columnList = "status")})
@Getter
@Setter
public class ReviewEntity extends BaseEntity {

    /**
     * Người đánh giá
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Sản phẩm được đánh giá
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 1 - 5 sao
     */
    @Column(nullable = false)
    private Integer rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ReviewStatus status = ReviewStatus.PENDING;


}