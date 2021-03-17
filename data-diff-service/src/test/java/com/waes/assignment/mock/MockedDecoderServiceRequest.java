package com.waes.assignment.mock;

import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceRequest;

import static com.waes.assignment.utils.DataDiffConstants.ENCODED_BASE64_VALUE;

/**
 * Class used to created default objects to be used as mock in the unit tests
 */
public class MockedDecoderServiceRequest {

    public static final DecoderServiceRequest MOCK_1 = new DecoderServiceRequest(ENCODED_BASE64_VALUE);
}
