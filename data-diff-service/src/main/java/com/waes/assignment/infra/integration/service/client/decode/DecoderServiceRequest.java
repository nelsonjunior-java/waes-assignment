package com.waes.assignment.infra.integration.service.client.decode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is an abstraction used for the decoder-service request
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
@AllArgsConstructor
public class DecoderServiceRequest {

    private String base64EncodedValue;
}
