package com.tomer.blogger.exceptions;

import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.AuthResponse;
import com.tomer.blogger.payloads.FileResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceExceptionHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validArgsHandler(MethodArgumentNotValidException ex) {
        var map = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((er) -> map.put(((FieldError) er).getField(), er.getDefaultMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<FileResponse> multipartExceptionHandler(FileUploadException ex) {
        return new ResponseEntity<>(new FileResponse("null", "Please Provide File \n " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<FileResponse> fileNotFoundExceptionHandler(FileNotFoundException ex) {
        return new ResponseEntity<>(new FileResponse("null", "Provided name is not valid file"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JWTException.class)
    public ResponseEntity<JWTException> expiredJwt(JWTException ex) {
        return new ResponseEntity<>(new JWTException(ex.getMessage(), ex.getCode()), HttpStatus.UNAUTHORIZED);

    }
}
