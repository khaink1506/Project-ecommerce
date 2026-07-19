package com.project.ecommerce.entity;

import com.project.ecommerce.enums.CouponStatus;
import com.project.ecommerce.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "coupons", indexes = {

        @Index(name = "idx_coupon_code", columnList = "code"),

        @Index(name = "idx_coupon_status", columnList = "status"),

        @Index(name = "idx_coupon_date", columnList = "start_date,end_date")})
@Getter
@Setter
public class CouponEntity extends BaseEntity {

    /**
     * Mã giảm giá
     * <p>
     * VD:
     * SALE10
     */
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, length = 30)
    private DiscountType discountType;

    /**
     * Nếu:
     * <p>
     * PERCENTAGE:
     * 10 = 10%
     * <p>
     * FIXED:
     * 50000 = 50k
     */
    @Column(name = "discount_value", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountValue;

    /**
     * Giới hạn giảm tối đa
     * <p>
     * VD:
     * giảm 20%
     * nhưng tối đa 100k
     */
    @Column(name = "max_discount", precision = 18, scale = 2)
    private BigDecimal maxDiscount;

    /**
     * Đơn tối thiểu
     */
    @Column(name = "minimum_order", precision = 18, scale = 2)
    private BigDecimal minimumOrder;

    /**
     * Tổng số coupon phát hành
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Đã sử dụng
     */
    @Column(name = "used_quantity")
    private Integer usedQuantity = 0;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CouponStatus status = CouponStatus.ACTIVE;


}