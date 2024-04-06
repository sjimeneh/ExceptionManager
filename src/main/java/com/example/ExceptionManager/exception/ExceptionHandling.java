package com.example.ExceptionManager.exception;

import com.example.ExceptionManager.util.ResponseMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseMessage<?>> handleResourceNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage<>(false,HttpStatus.NOT_FOUND.value(),ex.getMessage(),null));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseMessage<?>> handleResourceRunTimeException(RuntimeException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage<>(false,HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",null));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessage<>(false,HttpStatus.BAD_REQUEST.value(),"Exeption: Bad request",errors));
    }

}
