package com.waes.assignment.bdd.steps;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.waes.assignment.utils.DataDiffConstants.ENCODED_VALUE_FIELD_NAME;
import static com.waes.assignment.utils.DataDiffConstants.BASE64_ENCODED_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_ID;
import static com.waes.assignment.utils.ServicesConstants.DECODER_SERVICE_URL;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_BASE_API_URL;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX;
import static com.waes.assignment.helper.StubFiles.DECODER_SERVICE_OK_RESPONSE;

@Slf4j
public class SaveDataDiffStep extends AbstractStep {

    @Given("I want to save a left encoded value on the application endpoint")
    public void iWantToSaveALeftEncodedValueOnTheApplicationEndpoint() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }
}
