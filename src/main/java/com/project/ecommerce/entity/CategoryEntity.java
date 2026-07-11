package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))

public class CategoryEntity  implements Serializable {
    @Serial
    private static final long serialVersionUID = -1764746L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_activated")
    private boolean isActivated;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDeleted = false;
        this.isActivated = true;
    }
}
