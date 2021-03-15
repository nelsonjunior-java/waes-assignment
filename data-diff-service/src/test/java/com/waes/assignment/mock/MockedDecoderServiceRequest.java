package com.waes.assignment.mock;

import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceRequest;

import static com.waes.assignment.utils.DataDiffConstants.ENCODED_BASE64_VALUE;

public class MockedDecoderServiceRequest {

    public static final DecoderServiceRequest MOCK_1 = new DecoderServiceRequest(ENCODED_BASE64_VALUE);
}
