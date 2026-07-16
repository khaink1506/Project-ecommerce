package com.project.ecommerce.service.impl;

import com.project.ecommerce.config.SecurityConfig;
import com.project.ecommerce.converter.UserConverter;
import com.project.ecommerce.entity.AdminEntity;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.request.UserRequestDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.model.response.UserResponseDTO;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.existsByUserName(userRequestDTO.getUserName())){
            throw new InvalidRequestException("username đã tồn tại");
        }
        AdminEntity adminEntity = AdminEntity.builder()
                .userName(userRequestDTO.getUserName())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .build();

        userRepository.save(adminEntity);
        return ResponseDTO.builder()
                .message("Tạo user thành công")
                .data(userConverter.toUserResponseDTO(adminEntity))
                .build();
    }
}
