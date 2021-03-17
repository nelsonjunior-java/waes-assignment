package com.waes.assignment.unit.domain.decoder;

import com.waes.assignment.domain.decoder.impl.Base64DecoderImpl;
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
 * Class used for unit testing the Base64DecoderImpl class
 */
@ExtendWith(MockitoExtension.class)
public class Base64DecoderImplTest {

    @InjectMocks
    private Base64DecoderImpl base64Decoder;

    @ParameterizedTest
    @DisplayName("Should decode a base64 value")
    @MethodSource("provideValuesToTestBase64Decode")
    void ShouldReturnAListOfDifferenceObjectsWithTheSizeOfOne(String base64Value, String base64ValueDecodeValue) {

        String decodeValue = base64Decoder.decode(base64Value);

        assertThat(decodeValue).isEqualTo(base64ValueDecodeValue);
    }

    private static Stream<Arguments> provideValuesToTestBase64Decode() {
        return Stream.of(
                Arguments.of(
                        "Q2xlYW4gQ29kZSE=",
                        "Clean Code!"
                ),
                Arguments.of(
                        "V2Flcw==",
                        "Waes"
                ),
                Arguments.of(
                        "R2V6b25kaGVpZCE=",
                        "Gezondheid!"
                ),
                Arguments.of(
                        "QW52YWwgaXMgZGUgYmVzdGUgdmVyZGVkaWdpbmc=",
                        "Anval is de beste verdediging"
                ),
                Arguments.of(
                        "SGVsbG8sIHdvcmxkIQ==",
                        "Hello, world!"
                ),
                Arguments.of(
                        "SmF2YTEx",
                        "Java11"
                ),
                Arguments.of(
                        "eyAibmFtZSI6IkpvaG4iLCAiYWdlIjozMCwgImNpdHkiOiJVdHJlY2h0In0K",
                        "{ \"name\":\"John\", \"age\":30, \"city\":\"Utrecht\"}\n"
                ),
                Arguments.of(
                        "eyAibmFtZSI6IlZpbmNlbnQgdmFuIEdvZ2giLCAiYWdlIjozMCwgImNhciI6bnVsbCB9",
                        "{ \"name\":\"Vincent van Gogh\", \"age\":30, \"car\":null }"
                )
        );
    }
}
