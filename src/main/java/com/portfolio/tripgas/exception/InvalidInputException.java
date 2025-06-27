package com.portfolio.tripgas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(){ super("Invalid Input"); }

    public InvalidInputException(String message){ super(message); }

}
