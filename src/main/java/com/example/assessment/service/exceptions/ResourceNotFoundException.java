package com.example.assessment.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private final String message;
     public ResourceNotFoundException(String message) {
         super(message);
         this.message=message;
     }
}
