package com.project.ecommerce.entity;

import com.project.ecommerce.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders", indexes = {

        @Index(name = "idx_order_user", columnList = "user_id"),

        @Index(name = "idx_order_status", columnList = "order_status"),

        @Index(name = "idx_order_payment_status", columnList = "payment_status"),

        @Index(name = "idx_order_created", columnList = "created_at")})
@Getter
@Setter
public class OrderEntity extends BaseEntity {

    @Column(name = "order_code", nullable = false, unique = true, length = 30)
    private String orderCode;

    @Column(name = "receiver_name", nullable = false, length = 150)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false, length = 20)
    private String receiverPhone;

    @Column(name = "receiver_province", nullable = false, length = 100)
    private String receiverProvince;

    @Column(name = "receiver_district", nullable = false, length = 100)
    private String receiverDistrict;

    @Column(name = "receiver_ward", nullable = false, length = 100)
    private String receiverWard;

    @Column(name = "receiver_street", nullable = false, length = 255)
    private String receiverStreet;

    @Column(length = 500)
    private String note;

    @Column(name = "shipping_fee", nullable = false, precision = 18, scale = 2)
    private BigDecimal shippingFee = BigDecimal.ZERO;

    @Column(name = "discount_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "total_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 30)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 30)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    /**
     * Người đặt hàng
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * Chi tiết đơn hàng
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> details = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private PaymentEntity payment;

}