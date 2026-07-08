package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -74643736473437L;

    @Column(name = "created_date")
    @CreatedDate
    LocalDateTime createdDate;

    @Column(name = "created_by")
    @CreatedBy
    String createdBy;

    @Column(name = "modified_date")
    @LastModifiedDate
    LocalDateTime modifiedDate;

    @Column(name = "modified_By")
    @LastModifiedBy
    String modifiedBy;

}
