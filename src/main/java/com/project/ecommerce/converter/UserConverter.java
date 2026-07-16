package com.project.ecommerce.converter;

import com.project.ecommerce.entity.AdminEntity;
import com.project.ecommerce.model.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserResponseDTO toUserResponseDTO(AdminEntity adminEntity){
        return modelMapper.map(adminEntity, UserResponseDTO.class);
    }
}
