package com.waes.assignment.controller.dto.response;

/**
 * This class has the status used in the DiffResponse class
 * It separates the status from the domain layer from the application layer
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
public enum DiffResponseStatus {
    EQUAL,
    DIFFERENT_SIZE,
    SAME_SIZE_WITH_DIFFERENCES
}
