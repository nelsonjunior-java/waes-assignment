package com.waes.assignment.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * This class has the difference between two values when they have the same size
 * The values are set on this class after a comparison is made
 * It uses Lombok to remove unnecessary boilerplate code (get/setter, equal and hashcode, etc)
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Difference {

    private Integer offset;
    private Integer length;
}
