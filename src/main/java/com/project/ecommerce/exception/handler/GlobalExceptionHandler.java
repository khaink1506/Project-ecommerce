package com.project.ecommerce.exception.handler;

import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResponseDTO> handleInvalidRequestException(InvalidRequestException e){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(e.getMessage());
        List<String> details = new ArrayList<>();
        responseDTO.setDetails(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
