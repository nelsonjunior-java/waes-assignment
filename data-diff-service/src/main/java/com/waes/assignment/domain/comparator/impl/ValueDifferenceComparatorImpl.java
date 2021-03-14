package com.waes.assignment.domain.comparator.impl;

import com.waes.assignment.domain.comparator.ValueDifferenceComparator;
import com.waes.assignment.domain.model.Difference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ValueDifferenceComparator interface where it receives two values
 * and returns the result of the comparison made
 */
@Component
public class ValueDifferenceComparatorImpl implements ValueDifferenceComparator<List<Difference>, String> {

    private static final Integer OFFSET_INITIAL_VALUE = 0;
    private static final Integer LENGTH_INITIAL_VALUE = 0;

    /**
     * This method iterates over two strings in order to find the any character difference
     * In case there is a difference it will store it in a List of Difference objects
     * The difference objects keeps the offset and the length of the sequencial difference found
     * @param leftValue first value to be compared
     * @param rightValue second value to be compared
     * @return list with Difference objects with an offset and length attributes
     */
    @Override
    public List<Difference> compare(String leftValue, String rightValue) {

        Integer offSet = OFFSET_INITIAL_VALUE;
        Integer length = LENGTH_INITIAL_VALUE;

        List<Difference> differences = new ArrayList<>();

        for (int index = 0; index < leftValue.length(); index++) {

            if (areCharactersDifferent(leftValue, rightValue, index)) {

                if (isOffSetDifferentFromInitialValue(offSet)) {
                    length++;
                } else {
                    offSet = index;
                    length++;
                }

            } else {
                if (isOffSetDifferentFromInitialValue(offSet)) {
                    differences.add(new Difference(offSet, length));
                    offSet = OFFSET_INITIAL_VALUE;
                    length = LENGTH_INITIAL_VALUE;
                }
            }
        }

        if (isOffSetDifferentFromInitialValue(offSet)) {
            differences.add(new Difference(offSet, length));
        }

        return differences;
    }

    /**
     * Checks if the character in the position provided are different on the two Strings
     * @param valueOne string value
     * @param valueTwo string value
     * @param index index to compare
     * @return it will return true when the two characters compared are not the same
     */
    public boolean areCharactersDifferent(String valueOne, String valueTwo, int index) {
        return valueOne.charAt(index) != valueTwo.charAt(index);
    }

    /**
     * Checks if the provided offset value is different from the OFFSET_INITIAL_VALUE value
     * @param offset string value
     * @return It will return true when the offset value provided is different from
     * what is defined in the OFFSET_INITIAL_VALUE
     */
    public boolean isOffSetDifferentFromInitialValue(Integer offset) {
        return offset != OFFSET_INITIAL_VALUE;
    }

}
