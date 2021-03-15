package com.waes.assignment.infra.integration.service.client.decode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is an abstraction from the decoder-service response
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
@AllArgsConstructor
public class DecoderServiceResponse {

    private String decodeValue;
}
