package com.project.ecommerce.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "username không được bỏ trống")
    private String userName;

    @NotBlank(message = "password không được bỏ trống")
    private String password;
}
