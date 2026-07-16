package com.project.ecommerce.api;

import com.project.ecommerce.model.request.UserRequestDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import com.project.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok().body(userService.createUser(userRequestDTO));
    }
}
