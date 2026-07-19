package com.project.ecommerce.converter;

import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.model.request.RegisterRequest;
import com.project.ecommerce.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserEntity toUserEntity(RegisterRequest request){
        return modelMapper.map(request, UserEntity.class);
    }

    public UserResponse toUserResponse(UserEntity user){
        return modelMapper.map(user, UserResponse.class);
    }
}
