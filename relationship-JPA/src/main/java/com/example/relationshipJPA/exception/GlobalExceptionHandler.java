package com.example.relationshipJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgsNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            response.put(fieldName, message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> noSuchElementException(NoSuchElementException ex) {
        String message = ex.getMessage();
        ApiResponse response = new ApiResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
