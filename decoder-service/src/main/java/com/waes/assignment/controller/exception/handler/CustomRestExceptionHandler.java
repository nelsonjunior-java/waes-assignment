package com.waes.assignment.controller.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice("com.waes.assignment")
public class CustomRestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorFieldResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

        log.error("method=handleMethodArgumentNotValidException, exception={}", exception.getMessage());

        final List<ErrorFieldResponse> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(new ErrorFieldResponse(error.getField(), error.getDefaultMessage()));
        }

        return new ApiErrorFieldResponse(errors);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorFieldResponse handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {

        log.error("method=handleIllegalArgumentException, exception={}", exception.getMessage());

        final List<ErrorFieldResponse> errors = new ArrayList<>();
        errors.add(new ErrorFieldResponse(exception.getName(), exception.getMessage()));

        return new ApiErrorFieldResponse(errors);
    }
}
