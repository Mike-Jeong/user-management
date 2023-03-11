package com.assessmentmin.usermanagement.exception;

import com.assessmentmin.usermanagement.exception.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ErrorResponse userExceptionHandler(UserException e) {
        return new ErrorResponse(e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ErrorResponse jwtExceptionHandler(AuthException e) {
        return new ErrorResponse(e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

}
