package br.com.jmsstudio.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}
