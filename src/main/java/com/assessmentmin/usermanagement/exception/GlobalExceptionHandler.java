package com.assessmentmin.usermanagement.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Model model, Exception e, HttpServletRequest request) {

        System.out.println(e.getMessage());
        model.addAttribute("errorMessage", e.getMessage().isBlank() ? "404 NOT FOUND" : e.getMessage());
        return "error";
    }

}
