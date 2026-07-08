package com.project.ecommerce.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO{
    private Object data;
    private String message;
    private List<String> details;
}
