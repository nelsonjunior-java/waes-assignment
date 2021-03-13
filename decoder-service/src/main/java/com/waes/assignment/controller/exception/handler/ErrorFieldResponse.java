package com.waes.assignment.controller.exception.handler;

public class ErrorFieldResponse {

    private final String field;

    private final String message;

    public ErrorFieldResponse(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
