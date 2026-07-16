package com.project.ecommerce.service;

import com.project.ecommerce.model.request.UserRequestDTO;
import com.project.ecommerce.model.response.ResponseDTO;

public interface UserService {
    ResponseDTO createUser(UserRequestDTO userRequestDTO);
}
