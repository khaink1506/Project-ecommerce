package com.project.ecommerce.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO {
    private int status;
    private String code;
    private String message;
    private Object data;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}
