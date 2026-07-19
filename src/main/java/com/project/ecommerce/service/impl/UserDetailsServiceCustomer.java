package com.project.ecommerce.service.impl;

import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.enums.UserStatus;
import com.project.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustomer implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username không tồn tại"));

        if (userEntity.getStatus() == UserStatus.INACTIVE) {
            throw new DisabledException("Account is inactive");
        }
        if (userEntity.getStatus() == UserStatus.BLOCKED) {
            throw new LockedException("Account is locked");
        }
        return userEntity;

    }
}
