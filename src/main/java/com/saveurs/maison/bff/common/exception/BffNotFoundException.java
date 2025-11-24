package com.saveurs.maison.bff.common.exception;

public class BffNotFoundException extends RuntimeException {

    public BffNotFoundException(String message) {
        super(message);
    }
}
