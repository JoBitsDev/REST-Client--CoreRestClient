/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.utils;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {

        if (ex instanceof ResponseStatusException) {
            return handleResponseStatusEx((ResponseStatusException) ex);
        }

        return handleGeneral(ex);

    }
    
    private ResponseEntity<Object> handleGeneral(
            Exception ex) {
        List<String> errors = new ArrayList();
        errors.add(ex.getMessage());
        errors.add(ex.toString());
        for (StackTraceElement s : ex.getStackTrace()) {
            if (s.getClassName().contains("com.jobits.pos")) {
                errors.add("Trace: {Class: "
                        + s.getClassName()
                        + " [" + s.getLineNumber() + "]}");
            }
        }
        com.jobits.pos.core.repo.impl.AbstractRepository.transactionErrorListener.propertyChange(new PropertyChangeEvent(this, "ERROR", 0, 1));
        ApiError apiError
                = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage(), errors);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getLocalizedMessage(), ex);

        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    private ResponseEntity<Object> handleResponseStatusEx(
            ResponseStatusException ex) {
        List<String> errors = new ArrayList();
        errors.add(ex.getMessage());
        errors.add(ex.toString());
        for (StackTraceElement s : ex.getStackTrace()) {
            if (s.getClassName().contains("com.jobits.pos")) {
                errors.add("Trace: {Class: "
                        + s.getClassName()
                        + " [" + s.getLineNumber() + "]}");
            }
        }
        com.jobits.pos.core.repo.impl.AbstractRepository.transactionErrorListener.propertyChange(new PropertyChangeEvent(this, "ERROR", 0, 1));
        ApiError apiError
                = new ApiError(ex.getRawStatusCode(), ex.getMessage(), errors);
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

}
