package com.waes.assignment.domain.decoder.impl;

import com.waes.assignment.domain.decoder.Decoder;
import org.apache.commons.codec.binary.Base64;

public class Base64DecoderImpl implements Decoder <String, String>{

    @Override
    public String decode(String encodedValue) {

        return Base64.decodeBase64(encodedValue).toString();
    }



}
