package com.waes.assignment.helper;

/**
 * This class has the files used for json response in the WireMock stubs
 */
public class StubFiles {

    private StubFiles() {
    }

    private static final String DECODER_SERVICE_PREFIX = "decoder-service/";

    /**
     * Stubs related to the decoder-service
     */
    public static final String DECODER_SERVICE_OK_RESPONSE = DECODER_SERVICE_PREFIX + "decoder_service_ok.json";
    public static final String DECODER_SERVICE_BIGGER_VALUE_OK_RESPONSE = DECODER_SERVICE_PREFIX + "decoder_service_bigger_value_ok.json";
    public static final String DECODER_SERVICE_SAME_SIZE_DIFF_CHAR_VALUE_OK_RESPONSE = DECODER_SERVICE_PREFIX + "decoder_service_same_size_diff_char_value_ok.json";




}
