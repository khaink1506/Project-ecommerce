package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "shopping_cart")
public class ShoppingCartEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -747374L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "total_prices")
    private double totalPrices;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "cart")
    private Set<CartItemEntity> cartItems;
}
