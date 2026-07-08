package com.project.ecommerce.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandRequestDTO {
    Long id;
    @NotBlank(message = "Tên không được để trống")
    String name;
}
