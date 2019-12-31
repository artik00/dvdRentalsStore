package com.artyom.dvdrentals.controller;

import com.artyom.dvdrentals.model.exception.CustomerNotFoundException;
import com.artyom.dvdrentals.model.exception.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DvdRentalsExceptionController {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> exception(CustomerNotFoundException exception) {
        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FilmNotFoundException.class)
    public ResponseEntity<Object> exception(FilmNotFoundException exception) {
        return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
    }
}
