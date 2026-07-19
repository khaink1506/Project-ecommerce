package com.project.ecommerce.service;

import com.project.ecommerce.entity.UserEntity;

public interface JwtGenerator {

    String generateAccessToken(UserEntity user);
    String generateRefreshToken(UserEntity user);
}
