package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "oders")
public class OrdersEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8434638338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "receiver_name", nullable = false)
    String receiverName;

    @Column(name = "receiver_phone", nullable = false)
    String receiverPhone;

    @Column(name = "receiver_address", nullable = false)
    String receiverAddress;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @Column(name = "payment_method", nullable = false)
    String paymentMethod;

    @Column(name = "order_date", nullable = false)
    LocalDateTime orderDate;

    @Column(name = "total_price", nullable = false)
    Double totalPrice;

    @Column(name = "status", nullable = false)
    String status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailsEntity> orderDetails = new ArrayList<>();
}
