package com.waes.assignment.infra.integration.service.client.decode;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This class defines the contract for a client in order to communicate with the external service decoder-service
 */
@FeignClient(value = "decoder-service", url = "${remote.decoder-service.base-url}")
public interface DecoderServiceClient {

    @PostMapping("/v1/decoder/base64")
    DecoderServiceResponse decode(@RequestBody DecoderServiceRequest encodedValue);

}
