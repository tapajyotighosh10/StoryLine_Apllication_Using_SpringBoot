package com.blogging.app.exceptions;

import com.blogging.app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse> resourcrNotFoundException(ResourceNotFoundException ex){
            String message = ex.getMessage();
            ApiResponse apiResponse= new ApiResponse(message,false);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,String>> handleMethodArgNotValidExteption(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) -> {
           String fieldName = ((FieldError)err).getField();
           String message = err.getDefaultMessage();
           resp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
        }
}
