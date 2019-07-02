package com.popova.fix.jobtest.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    @ExceptionHandler
    public ModelAndView errorHandler(HttpServletRequest request) {
        Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
        String exceptinMessage;
        if (e.getCause() != null)
            exceptinMessage = e.getCause().getMessage();
        else exceptinMessage = e.getMessage();
        return new ModelAndView("main", "exception", exceptinMessage);
    }

    @Override
    public String getErrorPath() {
        return "/";
    }
}
