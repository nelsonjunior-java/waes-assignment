package com.waes.assignment.domain.decoder;

/**
 * Defines the contract for a generic decoder that can be used in the future by this microservice
 * to decode other types of data as the demands arise
 * @param <R> input value type
 * @param <S> output value type
 */
public interface Decoder<I, R> {
    R decode(I encodedValue);
}
