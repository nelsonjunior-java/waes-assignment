package com.waes.assignment.domain.exception;

/**
 * Exception used for scenarios where a record was not found on the database
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }

}
