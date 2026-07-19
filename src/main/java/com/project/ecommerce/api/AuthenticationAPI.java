package com.project.ecommerce.api;

import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.request.RegisterRequest;
import com.project.ecommerce.model.response.LoginResponse;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationAPI {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok().body(authenticationService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequest registerRequest){
        return ResponseEntity.ok().body(authenticationService.register(registerRequest));
    }
}
