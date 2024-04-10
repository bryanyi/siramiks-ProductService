package com.siramiks.ProductService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ProductServiceException.class)
  public ResponseEntity<ErrorResponse> handleProductException(ProductServiceException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder()
            .errorMessage(exception.getMessage())
            .errorCode(exception.getErrorCode())
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

  }
}
