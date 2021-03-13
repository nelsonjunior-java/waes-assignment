package com.waes.assignment.controller.dto.response;

import lombok.Data;

@Data
public class Base64DecoderResponse {

    private String decodeValue;

    public static Base64DecoderResponse from(String decodeValue) {

        final Base64DecoderResponse domainAllowedResponse = new Base64DecoderResponse();
        domainAllowedResponse.setDecodeValue(decodeValue);

        return domainAllowedResponse;
    }
}
