package com.learn.SpringSecurity2025.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = Exception.class)
    public String exceptionHandler(Exception e){
        return String.format("<h2>Something went wrong. Please try again !</h2><p> Exception Occurred: %s </p>",
                e.getMessage());
    }
}
