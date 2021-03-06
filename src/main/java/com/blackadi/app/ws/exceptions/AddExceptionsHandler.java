package com.blackadi.app.ws.exceptions;

import java.util.Date;

import com.blackadi.app.ws.mobileappws.ui.model.response.ErrorMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// ControllerAdvice annotation will listen to all our HTTP methods and handle any error
@ControllerAdvice
public class AddExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

        // Create our custom error message
        String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.getLocalizedMessage() : ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
    public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {

        // Create our custom error message
        String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.getLocalizedMessage() : ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
