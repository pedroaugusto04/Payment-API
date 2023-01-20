/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exceptionhandler;

import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.exceptions.RoleTypeNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    
    @org.springframework.web.bind.annotation.ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFound(CardNotFoundException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Card not registered");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleIdNotFound(IdNotFoundException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Id not registered");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(CpfNotFoundException.class)
    public ResponseEntity<Object> handleCpfNotFound(CpfNotFoundException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("CPF not registered");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<Object> handleInvalidPayment(InvalidPaymentException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Invalid payment.Verify transaction data.\n"
                + "Obs: If credit card payment, is mandatory to inform cardNumber, cardCvv and cardHolderName");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler(RoleTypeNotFoundException.class)
    public ResponseEntity<Object> handleRoleTypeNotFound(RoleTypeNotFoundException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("This role does not exist.");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQL(SQLIntegrityConstraintViolationException ex,WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDescription("Database did not accept your request. Verify if your data is already registered.");
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);

    }

}
