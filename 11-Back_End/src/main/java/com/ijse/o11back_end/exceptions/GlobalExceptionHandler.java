package com.ijse.o11back_end.exceptions;

import com.ijse.o11back_end.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGenericException(Exception e){
        return new ResponseEntity<>(new APIResponse(500,e.getMessage(),null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new APIResponse(404, e.getMessage(), null),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<APIResponse> handlesValidationException(ValidationException e){
        return new ResponseEntity<>(new APIResponse(400, e.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handlesMethodArgumentNotValid(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getDefaultMessage(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(new APIResponse(400, e.getMessage(), errors), HttpStatus.BAD_REQUEST);
    }
}
