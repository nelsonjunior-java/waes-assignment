package com.waes.assignment.unit.service;

import com.waes.assignment.domain.decoder.Decoder;
import com.waes.assignment.domain.exception.BadRequestException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.service.impl.Base64DecoderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.waes.assignment.utils.DecoderConstants.EMPTY_ENCODED_BASE64_VALUE;
import static com.waes.assignment.utils.DecoderConstants.EMPTY_SPACE__ENCODED_BASE64_VALUE;
import static com.waes.assignment.utils.DecoderConstants.NOT_ENCODED_BASE64_VALUE;
import static com.waes.assignment.utils.DecoderConstants.ENCODED_BASE64_VALUE;

@ExtendWith(MockitoExtension.class)
public class Base64DecoderServiceImplTest {

    @InjectMocks
    private Base64DecoderServiceImpl base64DecoderService;

    @Mock
    private Decoder<String, String> decoder;

    @Mock
    private MessageHelper messageHelper;

    @Test
    @DisplayName("Should throw a BadRequestException when the encoded value is empty")
    void shouldThrowABusinessRuleExceptionWhenTryingToEvaluateTheDifferencesWithANullId() {

        assertThrows(BadRequestException.class, () ->
                base64DecoderService.decode(EMPTY_ENCODED_BASE64_VALUE)
        );

    }

    @Test
    @DisplayName("Should throw a BadRequestException when the encoded value has only empty spaces")
    void ShouldThrowABadRequestExceptionWhenTheEncodedValueHasOnlyEmptySpaces() {

        assertThrows(BadRequestException.class, () ->
                base64DecoderService.decode(EMPTY_SPACE__ENCODED_BASE64_VALUE)
        );

    }

    @Test
    @DisplayName("Should throw a BadRequestException when the value is not base64 encoded")
    void ShouldThrowABadRequestExceptionWhenTheValueIsNotBase64Encoded() {

        assertThrows(BadRequestException.class, () ->
                base64DecoderService.decode(NOT_ENCODED_BASE64_VALUE)
        );

    }

    @Test
    @DisplayName("Should return a String decoded")
    void ShouldReturnAStringDecoded() {

        when(decoder.decode(anyString())).thenReturn(NOT_ENCODED_BASE64_VALUE);

        var decodedValue = base64DecoderService.decode(ENCODED_BASE64_VALUE);

        assertThat(decodedValue).isEqualTo(NOT_ENCODED_BASE64_VALUE);
    }

}
