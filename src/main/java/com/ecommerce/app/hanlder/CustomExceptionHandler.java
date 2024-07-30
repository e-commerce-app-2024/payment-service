package com.ecommerce.app.hanlder;


import com.ecommerce.app.exception.PaymentIntegrationException;
import com.ecommerce.app.payload.AppResponse;
import com.ecommerce.app.payload.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        AppResponse appResponse = AppResponse.failedAppResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity(appResponse);
    }

    @ExceptionHandler(PaymentIntegrationException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(PaymentIntegrationException ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        AppResponse appResponse = AppResponse.failedAppResponse(error, HttpStatus.NOT_FOUND);
        return responseEntity(appResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        Error error = new Error(errors, HttpStatus.BAD_REQUEST);
        AppResponse appResponse = AppResponse.failedAppResponse(error, HttpStatus.BAD_REQUEST);
        return responseEntity(appResponse);
    }

    ResponseEntity responseEntity(AppResponse appResponse) {
        return new ResponseEntity(appResponse, HttpStatus.OK);
    }


}
