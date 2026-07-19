package com.project.ecommerce.service;

import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.request.RegisterRequest;
import com.project.ecommerce.model.response.LoginResponse;
import com.project.ecommerce.model.response.RegisterResponse;
import com.project.ecommerce.model.response.ResponseDTO;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);

    ResponseDTO register(RegisterRequest registerRequest);

}
