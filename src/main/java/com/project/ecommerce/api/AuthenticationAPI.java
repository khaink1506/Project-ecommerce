package com.project.ecommerce.api;

import com.project.ecommerce.model.request.LoginRequest;
import com.project.ecommerce.model.response.LoginResponse;
import com.project.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthenticationAPI {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok().body(authenticationService.login(loginRequest));
    }
}
