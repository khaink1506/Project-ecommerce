package com.project.ecommerce.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {
    Long id;
    String name;
    String description;
    Double costPrice;
    Double salePrice;
    Integer currentQuantity;
    String image;
    String categoryName;
    boolean isActivated;
}
