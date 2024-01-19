package com.example.backend;

public class DuplicateRecipeException extends RuntimeException {
    DuplicateRecipeException(){
        super("Could not add recipe because it already exists");
    }
}
