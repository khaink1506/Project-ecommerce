package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7346749493L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", unique = true, nullable = false)
    String userName;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "fullname", nullable = false)
    String fullName;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "status")
    Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private RoleEntity role;
}
