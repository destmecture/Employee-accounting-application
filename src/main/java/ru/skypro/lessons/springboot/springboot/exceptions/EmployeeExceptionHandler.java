package ru.skypro.lessons.springboot.springboot.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    Logger logger = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){
        logger.error("Unexpected error", exception);
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> idNotFoundException(IdNotFoundException idNotFoundException){
        logger.error(idNotFoundException.getMessage(), idNotFoundException);
        idNotFoundException.printStackTrace();
        return new ResponseEntity<>(idNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> PositionIdNotFoundExceptions(PositionIdNotFoundExceptions positionIdNotFoundExceptions){
        logger.error("Position is not exist", positionIdNotFoundExceptions);
        positionIdNotFoundExceptions.printStackTrace();
        return new ResponseEntity<>(positionIdNotFoundExceptions.getMessage(), HttpStatus.NOT_FOUND);
    }



}
