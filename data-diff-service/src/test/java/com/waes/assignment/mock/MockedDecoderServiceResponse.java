package com.waes.assignment.mock;

import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceResponse;

import static com.waes.assignment.utils.DataDiffConstants.NON_BASE64_DIFF_VALUE;

/**
 * Class used to created default objects to be used as mock in the unit tests
 */
public class MockedDecoderServiceResponse {

    public static final DecoderServiceResponse MOCK_1 = new DecoderServiceResponse(NON_BASE64_DIFF_VALUE);
}
