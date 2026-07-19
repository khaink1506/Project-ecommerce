package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "wishlists",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_wishlist", columnNames = {"user_id", "product_id"})},
        indexes = {
                @Index(name = "idx_wishlist_user", columnList = "user_id"),
                @Index(name = "idx_wishlist_product", columnList = "product_id")})
@Getter
@Setter
public class WishlistEntity extends BaseEntity {

    /**
     * User sở hữu wishlist item
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Product được yêu thích
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


}