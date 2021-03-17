package com.waes.assignment.bdd.steps;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.Given;

import static com.waes.assignment.utils.DecoderConstants.BASE64_VALUE_TO_DECODE;
import static com.waes.assignment.utils.DecoderConstants.DECODE_VALUE_JSON_PROPERTY;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_BASE_API_URL;

public class DecodeStep extends AbstractStep {

    @Given("I try to decode a value passing in the decodeValue body parameter an empty value")
    public void tryToDecodeAValuePassingInTheBodyParameterDecodeValueAnEmptyValue() {

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(DECODE_VALUE_JSON_PROPERTY, BASE64_VALUE_TO_DECODE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL);
    }
}
