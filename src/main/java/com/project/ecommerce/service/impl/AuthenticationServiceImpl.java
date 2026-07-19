package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.UserConverter;
import com.project.ecommerce.entity.RoleEntity;
import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.enums.RoleCode;
import com.project.ecommerce.enums.UserStatus;
import com.project.ecommerce.exception.BusinessException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.request.RegisterRequest;
import com.project.ecommerce.model.response.LoginResponse;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.response.UserResponse;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.AuthenticationService;
import com.project.ecommerce.service.JwtService;
import com.project.ecommerce.validate.RegisterValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final RegisterValidator registerValidator;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userResponse(UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .role(user.getRole().getCode())
                        .build())
                .build();
    }

    @Override
    @Transactional
    public ResponseDTO register(RegisterRequest registerRequest) {
        registerValidator.validate(registerRequest);
        RoleEntity roleEntity = roleRepository.findByCode(RoleCode.CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.ROLE_NOT_FOUND.getCode()));
        UserEntity user = userConverter.toUserEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmailVerified(false);
        user.setRole(roleEntity);
        userRepository.save(user);
        return ResponseDTO.builder()
                .message("Đăng kí thành công")
                .data(userConverter.toUserResponse(user))
                .build();
    }
}
