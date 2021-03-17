package com.waes.assignment.service.impl;

import com.waes.assignment.domain.decoder.Decoder;
import com.waes.assignment.domain.exception.BadRequestException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.service.Base64DecoderService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.waes.assignment.domain.message.MessageCode.ERROR_EMPTY_ENCODED_VALUE;
import static com.waes.assignment.domain.message.MessageCode.ERROR_NOT_BASE64_ENCODED_VALUE;

/**
 * This class is an implementation of the Base64DecoderService
 */
@Service
public class Base64DecoderServiceImpl implements Base64DecoderService {

    private final Decoder<String, String> decoder;
    private final MessageHelper messageHelper;

    public Base64DecoderServiceImpl(Decoder<String, String> decoder, MessageHelper messageHelper) {
        this.decoder = decoder;
        this.messageHelper = messageHelper;
    }

    /**
     * Decodes a base64 value
     *
     * @param encodedValue encoded value to be decoded
     * @return decoded value
     */
    @Override
    public String decode(String encodedValue) {

        performValidations(encodedValue);

        return decoder.decode(encodedValue);
    }

    /**
     * Perform some internal validations
     *
     * @param encodedValue encoded value to be validated
     */
    private void performValidations(String encodedValue) {

        if (StringUtils.isEmpty(encodedValue) || encodedValue.isBlank()) {
            throw new BadRequestException(messageHelper.get(ERROR_EMPTY_ENCODED_VALUE));
        }

        if (!Base64.isBase64(encodedValue.getBytes())) {
            throw new BadRequestException(messageHelper.get(ERROR_NOT_BASE64_ENCODED_VALUE));
        }
    }
}
