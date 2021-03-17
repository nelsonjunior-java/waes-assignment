package com.waes.assignment.bdd.steps;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.Given;

import static com.waes.assignment.utils.DecoderConstants.DECODE_VALUE_JSON_PROPERTY;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_BASE_API_URL;
import static com.waes.assignment.utils.DecoderConstants.EMPTY_SPACES_VALUE_TO_DECODE;
import static com.waes.assignment.utils.DecoderConstants.NOT_BASE64_VALUE_TO_DECODE;
import static com.waes.assignment.utils.DecoderConstants.EMPTY_VALUE_TO_DECODE;

public class DecodeStep extends AbstractStep {

    @Given("I try to decode a value passing in the decodeValue body parameter an empty value")
    public void tryToDecodeAValuePassingInTheBodyParameterDecodeValueAnEmptyValue() {

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(DECODE_VALUE_JSON_PROPERTY, EMPTY_VALUE_TO_DECODE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL);
    }

    @Given("I try to decode a value passing in the decodeValue body parameter with only white spaces")
    public void tryToDecodeAValuePassingInTheDecodeValueBodyParameterWithOnlyWhiteSpaces() {

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(DECODE_VALUE_JSON_PROPERTY, EMPTY_SPACES_VALUE_TO_DECODE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL);
    }

    @Given("I try to decode a value passing in the decodeValue body parameter a not base64 value")
    public void tryToDecodeAValuePassingInTheDecodeValueBodyParameterNotInBase64() {

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(DECODE_VALUE_JSON_PROPERTY, NOT_BASE64_VALUE_TO_DECODE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL);
    }

    @Given("I try to decode a base64 encode value like {string}")
    public void tryToDecodeABase64EncodeValueLike(String base64Encoded) {

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(DECODE_VALUE_JSON_PROPERTY, base64Encoded);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL);
    }
}
