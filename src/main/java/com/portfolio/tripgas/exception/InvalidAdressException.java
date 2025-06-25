package com.portfolio.tripgas.exception;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(){ super("Invalid input"); }

    public InvalidInputException(String message){ super(message); }

}
