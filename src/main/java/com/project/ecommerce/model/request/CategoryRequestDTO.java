package com.project.ecommerce.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequestDTO {
    Long id;
    @NotBlank(message = "Tên danh mục không được bỏ trống")
    String name;

    @NotBlank(message = "Mô tả không được để trống")
    String description;
}
