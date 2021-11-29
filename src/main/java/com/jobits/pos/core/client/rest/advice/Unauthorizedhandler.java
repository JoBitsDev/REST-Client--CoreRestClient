/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.core.client.rest.advice;

import com.jobits.pos.exception.UnAuthorizedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.web.servlet.function.RequestPredicates.headers;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@ControllerAdvice
public class Unauthorizedhandler extends ResponseEntityExceptionHandler {

    // Let Spring BasicErrorController handle the exception, we just override the status code
    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity handleUnauthorized(Exception e) throws IOException {
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
