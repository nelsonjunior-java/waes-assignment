package com.waes.assignment.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * This class abstracts all the information for a diff record that will be stored on the database
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
public class DiffRecord {

    @Id
    private Long id;
    private String leftValue;
    private String rightValue;

    public DiffRecord(Long id, String leftValue, String rightValue) {
        this.id = id;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
}
