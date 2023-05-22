package com.flashcardsapi.exceptions;

import com.flashcardsapi.entities.ErrorResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append(";");
        }
        ErrorResponseBody body = new ErrorResponseBody();
        body.setMessage(errorMessage.toString());
        body.setError("bad request");
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        body.setPath(path);
        body.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return handleExceptionInternal(ex, body, headers, status, request);
    }


    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseBody handleAuthenticationException(AuthenticationException exception) {
        ErrorResponseBody body = new ErrorResponseBody();
        body.setTimestamp(new Timestamp(System.currentTimeMillis()));
        body.setStatus(HttpStatus.UNAUTHORIZED.value());
        body.setPath("auth/login");
        body.setError("Authentication error");
        body.setMessage("Login failed. Check your email and password!");
            return body;
    }
}
