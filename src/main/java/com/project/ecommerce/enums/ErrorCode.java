package com.project.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Validate

    USERNAME_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "USERNAME_ALREADY_EXISTS",
            "Tên đăng nhập đã tồn tại"
    ),

    EMAIL_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "EMAIL_ALREADY_EXISTS",
            "Email đã được sử dụng"
    ),

    PHONE_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "PHONE_ALREADY_EXISTS",
            "Số điện thoại đã được sử dụng"
    ),

    PASSWORD_MISMATCH(
            HttpStatus.BAD_REQUEST,
            "PASSWORD_MISMATCH",
            "Mật khẩu xác nhận không khớp"
    ),


    // Resource

    ROLE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "ROLE_NOT_FOUND",
            "Không tìm thấy vai trò"
    ),

    USER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "USER_NOT_FOUND",
            "Không tìm thấy người dùng"
    ),



    // Authentication

    INVALID_CREDENTIALS(
            HttpStatus.UNAUTHORIZED,
            "INVALID_CREDENTIALS",
            "Tên đăng nhập hoặc mật khẩu không chính xác"
    ),

    ACCOUNT_DISABLED(
            HttpStatus.FORBIDDEN,
            "ACCOUNT_DISABLED",
            "Tài khoản đã bị vô hiệu hóa"
    ),

    ACCOUNT_LOCKED(
            HttpStatus.FORBIDDEN,
            "ACCOUNT_LOCKED",
            "Tài khoản đã bị khóa"
    ),

    EMAIL_NOT_VERIFIED(
            HttpStatus.FORBIDDEN,
            "EMAIL_NOT_VERIFIED",
            "Tài khoản chưa xác thực email"
    ),





    // JWT

    INVALID_TOKEN(
            HttpStatus.UNAUTHORIZED,
            "INVALID_TOKEN",
            "Token không hợp lệ hoặc sai định dạng"
    ),

    TOKEN_EXPIRED(

            HttpStatus.UNAUTHORIZED,
            "TOKEN_EXPIRED",
            "Token đã hết hạn"
    ),

    INVALID_TOKEN_SIGNATURE(
            HttpStatus.UNAUTHORIZED,
            "INVALID_TOKEN_SIGNATURE",
            "Chữ ký token không hợp lệ"
    ),

    INVALID_TOKEN_TYPE(
            HttpStatus.UNAUTHORIZED,
            "INVALID_TOKEN_TYPE",
            "Loại token không hợp lệ"
    ),

    INVALID_ISSUER(
            HttpStatus.UNAUTHORIZED,
            "INVALID_ISSUER",
            "Nguồn phát hành token không hợp lệ"
    ),

    INVALID_ALGORITHM(
            HttpStatus.UNAUTHORIZED,
            "INVALID_ALGORITHM",
            "Thuật toán mã hóa token không được hộ trợ"
    );


    private final HttpStatus status;

    private final String code;

    private final String message;

}
