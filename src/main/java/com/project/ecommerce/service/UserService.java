package com.project.ecommerce.service;

import com.project.ecommerce.model.request.UserRequestDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    ResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserDetails loadUserByUsername(String username);
}
