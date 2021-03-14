package com.waes.assignment.infra.integration.service.client.decode;

import lombok.Data;

/**
 * This class is an abstraction from the decoder-service response
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
public class DecoderServiceResponse {

    private String decodeValue;
}
