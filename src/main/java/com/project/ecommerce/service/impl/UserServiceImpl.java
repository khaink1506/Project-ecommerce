package com.project.ecommerce.service.impl;

import com.project.ecommerce.converter.UserConverter;
import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.model.request.UserRequestDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
      return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
