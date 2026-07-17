package com.project.ecommerce.service;

import com.project.ecommerce.entity.AdminEntity;

public interface JwtService {

    String generateAccessToken(AdminEntity adminEntity);

    String generateRefreshToken(AdminEntity adminEntity);
}
