package com.project.ecommerce.exception.handler;

import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.exception.BusinessException;
import com.project.ecommerce.exception.InvalidJwtException;
import com.project.ecommerce.exception.InvalidRequestException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.model.response.JwtResponseDTO;
import com.project.ecommerce.model.response.ResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ResponseDTO> handleInvalidRequestException(InvalidRequestException e){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(e.getMessage());
        List<String> details = new ArrayList<>();
        responseDTO.setDetails(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Dữ liệu không hợp lệ");
        List<String> details = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        responseDTO.setDetails(details);
        return ResponseEntity.badRequest().body(responseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleReSourceNotFoundException(ResourceNotFoundException e){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(e.getMessage());
        List<String> details = new ArrayList<>();
        responseDTO.setDetails(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<JwtResponseDTO> handleReBusinessException(BusinessException e){
        ErrorCode errorCode = e.getErrorCode();
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setCode(errorCode.getCode());
        jwtResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(jwtResponseDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Dữ liệu đã tồn tại hoặc không hợp lệ");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<JwtResponseDTO> handleInvalidJwtException(InvalidJwtException e) {
        ErrorCode errorCode = e.getErrorCode();
        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setCode(errorCode.getCode());
        jwtResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(jwtResponseDTO);
    }

}
