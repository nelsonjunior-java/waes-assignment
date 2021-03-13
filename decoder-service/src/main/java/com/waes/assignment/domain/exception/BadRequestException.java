package com.waes.assignment.domain.exception;

/**
 * Exception used for scenarios where the user provided some wrong data on the request
 * therefore it is not possible to complete it successfully
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }
}
