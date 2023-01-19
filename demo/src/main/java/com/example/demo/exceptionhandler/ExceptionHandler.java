/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exceptionhandler;

import com.example.demo.exceptions.CardInvalidException;
import com.example.demo.exceptions.InvalidPaymentException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author pedro
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Field> fields = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) objectError).getField();
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            fields.add(new Field(name, message));
        }
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Invalid field(s)");
        error.setFields(fields);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CardInvalidException.class)
    public ResponseEntity<Object> handleCardNotFound(CardInvalidException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Card not registered");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<Object> handleInvalidPayment(InvalidPaymentException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Invalid payment.Verify transaction data.");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }

}
