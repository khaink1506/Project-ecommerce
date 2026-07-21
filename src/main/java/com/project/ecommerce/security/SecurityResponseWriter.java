package com.project.ecommerce.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.model.response.JwtResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityResponseWriter {
    private final ObjectMapper objectMapper;

    public void write(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        JwtResponseDTO responseDTO = JwtResponseDTO.builder()
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        objectMapper.writeValue(response.getOutputStream(), responseDTO);
    }
}
