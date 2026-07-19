package com.project.ecommerce.validate;

import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.exception.BusinessException;
import com.project.ecommerce.model.request.RegisterRequest;
import com.project.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterValidator {

    private final UserRepository userRepository;

    public void validate(RegisterRequest registerRequest){
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhone(registerRequest.getPhone())) {
            throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);
        }

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }
}
