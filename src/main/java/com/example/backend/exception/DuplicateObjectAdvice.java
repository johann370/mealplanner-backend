package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateObjectAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateObjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String duplicateObjectHandler(DuplicateObjectException ex) {return ex.getMessage();}
}
