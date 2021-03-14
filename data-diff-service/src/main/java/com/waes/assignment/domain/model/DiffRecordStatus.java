package com.waes.assignment.domain.model;

/**
 * This enum has all the DiffRecord statuses
 */
public enum DiffRecordStatus {
    WAITING_EVALUATION,
    EQUAL,
    DIFFERENT_SIZE,
    SAME_SIZE_WITH_DIFFERENCES
}
