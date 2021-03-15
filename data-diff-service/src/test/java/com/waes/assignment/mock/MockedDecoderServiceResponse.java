package com.waes.assignment.mock;

import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceResponse;

import static com.waes.assignment.utils.DataDiffConstants.NON_BASE64_DIFF_VALUE;

public class MockedDecoderServiceResponse {

    public static final DecoderServiceResponse MOCK_1 = new DecoderServiceResponse(NON_BASE64_DIFF_VALUE);
}
