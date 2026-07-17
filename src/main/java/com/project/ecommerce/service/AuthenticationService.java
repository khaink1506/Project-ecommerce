package com.project.ecommerce.service;

import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
