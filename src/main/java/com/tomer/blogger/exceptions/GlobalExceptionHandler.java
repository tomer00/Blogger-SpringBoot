package com.tomer.blogger.exceptions;

import com.tomer.blogger.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceExceptionHandler() {
        return new ResponseEntity<>(new ApiResponse("Not Found",404), HttpStatus.NOT_FOUND);
    }
}
