package com.project.ecommerce.service;

import com.project.ecommerce.enums.TokenType;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtVerifier {

    Jwt verifyToken(String token, TokenType expectedType);
}
