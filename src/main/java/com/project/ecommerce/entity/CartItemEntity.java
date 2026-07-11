package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cart_item")

public class CartItemEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8325734587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", unique = true)
    private ShoppingCartEntity cart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}

