package com.inditex.prices_service.exception;

public class MissingParameterException extends RuntimeException{
    public MissingParameterException(String message){
        super(message);
    }
}
