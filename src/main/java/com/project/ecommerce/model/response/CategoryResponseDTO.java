package com.project.ecommerce.model.response;

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
public class CategoryResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -17624454746L;

    private Long id;
    private String name;
    private String description;
    private boolean isDeleted;
    private boolean isActivated;
}
