package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "cart_items", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cart_product", columnNames = {"cart_id", "product_id"})},
        indexes = {
            @Index(
                    name = "idx_cart_item_cart",
                    columnList = "cart_id"),

            @Index(
                    name = "idx_cart_item_product",
                    columnList = "product_id")})
@Getter
@Setter
public class CartItemEntity extends BaseEntity {


    /**
     * Cart chứa item này
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;


    /**
     * Product trong giỏ hàng
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


    /**
     * Số lượng mua
     */
    @Column(nullable = false)
    private Integer quantity = 1;


    /**
     * Giá tại thời điểm thêm vào cart
     */
    @Column(name = "unit_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;


}