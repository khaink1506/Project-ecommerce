package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses",
        indexes = {
        @Index(
                name = "idx_address_user",
                columnList = "user_id"),
                @Index(
                        name = "idx_address_default",
                        columnList = "is_default")})
@Getter
@Setter
public class AddressEntity extends BaseEntity {

    @Column(name = "receiver_name", nullable = false, length = 150)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false, length = 20)
    private String receiverPhone;

    @Column(name = "province", nullable = false, length = 100)
    private String province;


    @Column(name = "district", nullable = false, length = 100)
    private String district;


    @Column(name = "ward", nullable = false, length = 100)
    private String ward;


    @Column(name = "street", nullable = false, length = 255)
    private String street;


    @Column(name = "postal_code", length = 20)
    private String postalCode;


    @Column(name = "is_default", nullable = false)
    private Boolean defaultAddress = false;


    /**
     * Address thuộc User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}