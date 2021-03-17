package com.waes.assignment.domain.comparator;

/**
 * This interface creates a contract for creating a comparator between values
 * It will always receive two values of the same type and then return the result
 */
public interface ValueDifferenceComparator<T, L> {

    T compare(L leftValue, L rightValue);
}
