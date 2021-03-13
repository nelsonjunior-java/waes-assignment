package com.waes.assignment.controller.exception.handler;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorFieldResponse {

    private final List<ErrorFieldResponse> errors;

    public ApiErrorFieldResponse(List<ErrorFieldResponse> errors) {
        super();
        this.errors = errors;
    }

    public ApiErrorFieldResponse(ErrorFieldResponse error) {
        super();
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public List<ErrorFieldResponse> getErrors() {
        return errors;
    }
}
