package com.inditex.prices_service.exception;

public class PriceNotFoundException extends RuntimeException{

    public PriceNotFoundException(String message){
        super(message);
    }
}
