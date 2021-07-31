package com.project.orderitem.handler;

import com.project.orderitem.exception.ProductInsertionFailure;
import com.project.orderitem.exception.ProductUnavailable;
import com.project.orderitem.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ProductExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductUnavailable.class)
    public ResponseEntity<Object> handleException(ProductUnavailable exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(new Date());
        response.setMessage("Product Unavailable");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        return entity;
    }

    @ExceptionHandler(value = ProductInsertionFailure.class)
    public ResponseEntity<Object> handleException(ProductInsertionFailure exception){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(new Date());
        response.setMessage("Product creation failed");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        return entity;
    }
}
