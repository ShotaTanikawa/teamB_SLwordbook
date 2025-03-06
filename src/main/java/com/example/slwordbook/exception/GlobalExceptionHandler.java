package com.example.slwordbook.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MyUniqueConstraintViolationException.class)
    public Object handleUniqueExceptions(Exception ex) {
        ModelAndView mav = new ModelAndView("error/custom-error");
        mav.addObject("errorMessage", ex.getMessage());
        return mav;
    }
}
