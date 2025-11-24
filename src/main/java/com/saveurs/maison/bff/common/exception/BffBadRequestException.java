package com.saveurs.maison.bff.common.exception;

public class BffBadRequestException extends RuntimeException {

    public BffBadRequestException(String message) {
        super(message);
    }
}
