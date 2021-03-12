package com.waes.assignment.domain.model;

import lombok.Data;

@Data
public class LeftAndRightRecord {

    private String id;
    private String leftValue;
    private String rightValue;

    public LeftAndRightRecord(String id, String leftValue, String rightValue) {
        this.id = id;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
}
