package com.waes.assignment.unit.domain.comparator;

import com.waes.assignment.domain.comparator.impl.ValueDifferenceComparatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class responsible for unit testing the ValueDifferenceComparatorImpl class
 */
@ExtendWith(MockitoExtension.class)
public class ValueDifferenceComparatorImplTest {

    @InjectMocks
    private ValueDifferenceComparatorImpl differenceComparator;

    @ParameterizedTest
    @DisplayName("Should return a List of Difference objects with the size of one")
    @MethodSource("provideValuesToTestCaseOne")
    void ShouldReturnAListOfDifferenceObjectsWithTheSizeOfOne(String leftValue, String rightValue) {

        var list = differenceComparator.compare(leftValue, rightValue);

        assertThat(list).hasSize(1);
    }

    private static Stream<Arguments> provideValuesToTestCaseOne() {
        return Stream.of(
                Arguments.of(
                        "aaaaaaaaa",
                        "aaaabbbbb"
                ),
                Arguments.of(
                        "HHHHHHHHHHH",
                        "AHHHHHHHHHH"
                ),
                Arguments.of(
                        "{ \"name\":\"Marco Van Basten\", \"age\":56",
                        "{ \"name\":\"Marco van Basten\", \"age\":56"
                ),
                Arguments.of(
                        "monday",
                        "sunday"
                ),
                Arguments.of(
                        "Brazil",
                        "Brasil"
                ),
                Arguments.of(
                        "PSV EindHoven",
                        "PSV eindHoven"
                )
        );
    }

    @ParameterizedTest
    @DisplayName("Should return a List of Difference objects having the attribute length equals to four")
    @MethodSource("provideValuesToTestCaseTwo")
    void ShouldReturnAListOfDifferenceObjectsHavingTheAttributeLenghEqualsToFour(String leftValue, String rightValue) {

        var list = differenceComparator.compare(leftValue, rightValue);

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getLength()).isEqualTo(4);
    }

    private static Stream<Arguments> provideValuesToTestCaseTwo() {
        return Stream.of(
                Arguments.of(
                        "aaaaaaaaa",
                        "aaaaabbbb"
                ),
                Arguments.of(
                        "HHHHHHHHHHH",
                        "AAAAHHHHHHH"
                ),
                Arguments.of(
                        "{ \"name\":\"Marco Van BASTEn\", \"age\":56",
                        "{ \"name\":\"Marco Van Basten\", \"age\":56"
                ),
                Arguments.of(
                        "moNDay",
                        "sunday"
                ),
                Arguments.of(
                        "AAAAaaaaAAAAAA",
                        "AAAAAAAAAAAAAA"
                ),
                Arguments.of(
                        "aaaaa",
                        "bbbba"
                )
        );
    }


    @ParameterizedTest
    @DisplayName("Should return a List of Difference objects having the attribute offset equals to four")
    @MethodSource("provideValuesToTestCaseThree")
    void ShouldReturnAListOfDifferenceObjectsHavingTheAttributeOffsetEqualsToTwo(String leftValue, String rightValue) {

        var list = differenceComparator.compare(leftValue, rightValue);

        assertThat(list).hasSize(1);
        assertThat(list.get(0).getOffset()).isEqualTo(2);
    }

    private static Stream<Arguments> provideValuesToTestCaseThree() {
        return Stream.of(
                Arguments.of(
                        "aaaaaaaaa",
                        "aabbaaaaa"
                ),
                Arguments.of(
                        "HHHHHHHHHHH",
                        "HHOHHHHHHHH"
                ),
                Arguments.of(
                        "monday",
                        "moNDAY"
                ),
                Arguments.of(
                        "waes",
                        "waES"
                ),
                Arguments.of(
                        "java11",
                        "jaVa11"
                )
        );
    }


    @ParameterizedTest
    @DisplayName("Should return a List of Difference objects having the size of four")
    @MethodSource("provideValuesToTestCaseFour")
    void ShouldReturnAListOfDifferenceObjectsHavingTheSizeOfFour(String leftValue, String rightValue) {

        var list = differenceComparator.compare(leftValue, rightValue);

        assertThat(list).hasSize(4);
    }


    private static Stream<Arguments> provideValuesToTestCaseFour() {
        return Stream.of(
                Arguments.of(
                        "aaaaaaaaa",
                        "abababaab"
                ),
                Arguments.of(
                        "aaaaaaaaaaaaaaaa",
                        "hhhaaahaaaahhhah"
                ),
                Arguments.of(
                        "{ \"nAme\":\"JoHN\", \"age\":30, \"CAR\":null }",
                        "{ \"name\":\"John\", \"age\":25, \"car\":null }"
                ),
                Arguments.of(
                        "hello world!!!",
                        "Hello-World?!?"
                ),
                Arguments.of(
                        "Aanval is de beste verdediging",
                        "AanvaL iS de BESTE VERDEDIGING"
                ),
                Arguments.of(
                        "software",
                        "SoFTwArE"
                )
        );
    }


}
