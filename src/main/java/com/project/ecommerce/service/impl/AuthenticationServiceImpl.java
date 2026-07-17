package com.project.ecommerce.service.impl;

import com.project.ecommerce.entity.AdminEntity;
import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.response.LoginResponse;
import com.project.ecommerce.service.AuthenticationService;
import com.project.ecommerce.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        AdminEntity admin = (AdminEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(admin);
        String refreshToken = jwtService.generateRefreshToken(admin);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
