package com.learn.SpringSecurity2025.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = Exception.class)
    public String exceptionHandler(Exception e){
        log.error("Error Occurred: {}", e.getMessage());
        return String.format("<h2>Something went wrong. Please try again !</h2><p> Exception Occurred: %s </p>",
                e.getMessage());
    }
}
