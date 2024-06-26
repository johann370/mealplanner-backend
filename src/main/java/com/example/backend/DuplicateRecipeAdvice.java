package com.example.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateRecipeAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateRecipeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String duplicateRecipeHandler(DuplicateRecipeException ex) {return ex.getMessage();}
}
