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
public class LoginRequest {

    @NotBlank(message = "Username không được bỏ trống")
    private String username;

    @NotBlank(message = "Password không được bỏ trống")
    private String password;
}
