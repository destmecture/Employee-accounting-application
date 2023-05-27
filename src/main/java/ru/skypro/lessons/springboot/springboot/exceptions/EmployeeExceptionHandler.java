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
    public ResponseEntity<?> handleIOException(IOException ioException){
        ioException.printStackTrace();
        return new ResponseEntity<>(ioException.getCause(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getCause(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> sqlException(SQLException sqlException){
        sqlException.printStackTrace();
        return new ResponseEntity<>(sqlException.getCause(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<?> idNotFoundException(IdNotFoundException idNotFoundException){
        idNotFoundException.printStackTrace();
        return new ResponseEntity<>(idNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
