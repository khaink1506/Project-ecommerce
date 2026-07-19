package com.project.ecommerce.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String username;
    private String fullName;
    private String email;
    private String accessToken;
    private String refreshToken;
}
