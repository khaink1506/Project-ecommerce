package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "oders")
public class OrdersEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8434638338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "transition_id")
    private String transitionId;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "discount_price")
    private double discountPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;
}
