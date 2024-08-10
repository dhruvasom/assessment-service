package com.example.assessment.service.exceptions.handler;

import com.example.assessment.service.dto.ErrorResponseDTO;
import com.example.assessment.service.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundError(ResourceNotFoundException resourceNotFoundException) {
        logger.error(resourceNotFoundException.getMessage());
        ErrorResponseDTO response = new ErrorResponseDTO("AS-404", resourceNotFoundException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
