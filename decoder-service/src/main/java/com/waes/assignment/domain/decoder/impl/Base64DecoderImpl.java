package com.waes.assignment.domain.decoder.impl;

import com.waes.assignment.domain.decoder.Decoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * Implements the decoder contract in order to decode a base64 value
 * and return a decode one
 */
@Service
public class Base64DecoderImpl implements Decoder<String, String> {

    @Override
    public String decode(String encodedValue) {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedValue);
        String decodedString = new String(decodedBytes);

        return decodedString;
    }
}
