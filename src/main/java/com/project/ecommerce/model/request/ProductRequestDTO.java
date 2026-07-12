package com.project.ecommerce.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO  {

     Long id;

     @NotBlank(message = "Tên không được bỏ trống")
     String name;

     @NotBlank(message = "Mô tả không được để trống")
     String description;

     @Positive(message = "Giá nhập hàng phải lớn hơn hoặc bằng 0")
     Double costPrice;

     @Positive(message = "Giá bán phải lớn hơn hoặc bằng 0")
     Double salePrice;

     @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
     Integer currentQuantity;

     @NotNull(message = "Danh mục không được bỏ trống")
     Long categoryId;

     MultipartFile image;
}
