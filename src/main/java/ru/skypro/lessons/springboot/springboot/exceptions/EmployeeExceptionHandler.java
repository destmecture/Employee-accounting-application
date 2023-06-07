package ru.skypro.lessons.springboot.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> idNotFoundException(IdNotFoundException idNotFoundException){
        idNotFoundException.printStackTrace();
        return new ResponseEntity<>(idNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> PositionIdNotFoundExceptions(PositionIdNotFoundExceptions positionIdNotFoundExceptions){
        positionIdNotFoundExceptions.printStackTrace();
        return new ResponseEntity<>(positionIdNotFoundExceptions.getMessage(), HttpStatus.NOT_FOUND);
    }



}
