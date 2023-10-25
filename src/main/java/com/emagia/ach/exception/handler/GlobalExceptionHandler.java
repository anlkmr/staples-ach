package com.emagia.ach.exception.handler;

import com.afrunt.jach.exception.ACHException;
import com.emagia.ach.exception.AnotherCustomException;
import com.emagia.ach.exception.ErrorResponse;
import com.emagia.ach.exception.MyCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyCustomException.class)
    public ResponseEntity<ErrorResponse> handleMyCustomException(MyCustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AnotherCustomException.class)
    public ResponseEntity<ErrorResponse> handleAnotherCustomException(AnotherCustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ACHException.class)
    public ResponseEntity<ErrorResponse> handleACHException(ACHException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
