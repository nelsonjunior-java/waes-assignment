package com.waes.assignment.domain.exception;

/**
 * Exception used for scenarios where some business validation was violated
 * therefore it is not possible to proceed with the action requested
 */
public class BusinessRuleException extends Exception {

    public BusinessRuleException(String msg) {
        super(msg);
    }
}
