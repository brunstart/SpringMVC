package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        log.error("handleException", e);

        model.addAttribute("message", "sorry!");

        return "error";
    }
}
