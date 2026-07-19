package com.project.ecommerce.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Username không được bỏ trống")
    @Size(min = 4, max = 50, message = "Username phải từ 4 đến 50 ký tự")
    @Pattern(
            regexp = "^[a-zA-Z0-9._]+$",
            message = "Username chỉ được chứa chữ cái, số, dấu chấm và dấu gạch dưới"
    )
    private String username;

    @NotBlank(message = "Họ tên không được bỏ trống")
    @Size(max = 150, message = "Họ tên tối đa 150 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 150, message = "Email tối đa 150 ký tự")
    private String email;

    @NotBlank(message = "Số điện thoại không được bỏ trống")
    @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự")
    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    @Size(min = 8, max = 100, message = "Mật khẩu phải từ 8 đến 100 ký tự")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Mật khẩu phải có ít nhất 1 chữ hoa, 1 chữ thường và 1 chữ số"
    )
    private String password;

    @NotBlank(message = "Xác nhận mật khẩu không khớp")
    private String confirmPassword;
}