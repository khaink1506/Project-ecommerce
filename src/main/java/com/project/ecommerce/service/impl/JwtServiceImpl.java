package com.project.ecommerce.service.impl;

import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.enums.TokenType;
import com.project.ecommerce.service.JwtGenerator;
import com.project.ecommerce.service.JwtService;
import com.project.ecommerce.service.JwtVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtVerifier jwtVerifier;
    private final JwtGenerator jwtGenerator;
    @Override
    public String generateAccessToken(UserEntity user) {
        return jwtGenerator.generateAccessToken(user);
    }

    @Override
    public String generateRefreshToken(UserEntity user) {
        return jwtGenerator.generateRefreshToken(user);
    }

    @Override
    public Jwt verifyToken(String token, TokenType expectedType) {
        return jwtVerifier.verifyToken(token, expectedType);
    }
}
