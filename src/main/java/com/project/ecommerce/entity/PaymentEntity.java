package com.project.ecommerce.entity;

import com.project.ecommerce.enums.PaymentMethod;
import com.project.ecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments", indexes = {

        @Index(name = "idx_payment_transaction", columnList = "transaction_id"),

        @Index(name = "idx_payment_status", columnList = "payment_status")})
@Getter
@Setter
public class PaymentEntity extends BaseEntity {

    /**
     * Một order có một payment
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private OrderEntity order;

    /**
     * Mã giao dịch từ payment gateway
     * <p>
     * VNPay:
     * transactionId
     * <p>
     * MoMo:
     * requestId
     */
    @Column(name = "transaction_id", length = 255)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 30)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    @Column(name = "gateway_response", columnDefinition = "TEXT")
    private String gatewayResponse;


}