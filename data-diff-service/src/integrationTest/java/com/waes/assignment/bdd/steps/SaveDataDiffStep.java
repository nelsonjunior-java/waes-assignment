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
import static com.waes.assignment.utils.DataDiffConstants.EMPTY_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.ONLY_WHITE_SPACES_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_ID;
import static com.waes.assignment.utils.ServicesConstants.DECODER_SERVICE_URL;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_BASE_API_URL;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX;
import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX;
import static com.waes.assignment.helper.StubFiles.DECODER_SERVICE_OK_RESPONSE;
import static com.waes.assignment.helper.StubFiles.DECODER_SERVICE_BIGGER_VALUE_OK_RESPONSE;
import static com.waes.assignment.helper.StubFiles.DECODER_SERVICE_SAME_SIZE_DIFF_CHAR_VALUE_OK_RESPONSE;

@Slf4j
public class SaveDataDiffStep extends AbstractStep {

    @Given("I save a left encoded value on the application endpoint")
    public void wantToSaveALeftEncodedValueOnTheApplicationEndpoint() {

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

    @Given("I save a left encoded value on the application endpoint using the id {long}")
    public void wantToSaveALeftEncodedValueOnTheApplicationEndpoint(Long id) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value on the application endpoint using the id {long}")
    public void wantToSaveARightEncodedValueOnTheApplicationEndpoint(Long id) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a left encoded value on the application endpoint using the id {long} and the encode value {string}")
    public void saveALeftEncodedValueOnTheApplicationEndpointUsingTheIdAndTheEncodeValue(Long id, String encodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, encodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value on the application endpoint using the id {long} and the encode value {string}")
    public void saveARightEncodedValueOnTheApplicationEndpointUsingTheIdAndTheEncodeValue(Long id, String encodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, encodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right bigger encoded value on the application endpoint using the id {long} and the encode value {string}")
    public void saveARightBiggerEncodedValueOnTheApplicationEndpointUsingTheIdAndTheEncodeValue(Long id, String encodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_BIGGER_VALUE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, encodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value with same size but with different characters on the application endpoint using the id {long} and the encode value {string}")
    public void saveArightEncodedValueWithSameSizeButWithDifferntCharacters(Long id, String encodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_SAME_SIZE_DIFF_CHAR_VALUE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, encodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + id + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value on the application endpoint")
    public void saveARightEncodedValueOnTheApplicationEndpoint() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a left encoded value on the application endpoint using a non numerical id value as {string}")
    public void saveALeftEncodedValueOnTheApplicationEndpoint(String nonNumericalValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + nonNumericalValue + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value on the application endpoint using a non numerical id value as {string}")
    public void saveARightEncodedValueOnTheApplicationEndpoint(String nonNumericalValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, BASE64_ENCODED_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + nonNumericalValue + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a left encoded value using an empty value on the body property base64EncodedValue")
    public void saveALeftEncodedValueUsingAnEmptyValueOnTheBodyProperty() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, EMPTY_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right encoded value using an empty value on the body property base64EncodedValue")
    public void saveARightEncodedValueUsingAnEmptyValueOnTheBodyProperty() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, EMPTY_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right value using in the body a base64EncodedValue property containing only empty spaces")
    public void saveARightEncodedValueInTheBodyPropertyContainingOnlyEmptySpaces() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, ONLY_WHITE_SPACES_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

    @Given("I save a left value using in the body a base64EncodedValue property containing only empty spaces")
    public void saveALeftEncodedValueInTheBodyPropertyContainingOnlyEmptySpaces() {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, ONLY_WHITE_SPACES_VALUE);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a left value using a not base64 encoded value like {string}")
    public void saveALeftEncodedValueInTheBodyPropertyContainingOnlyEmptySpaces(String notBase64EncodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, notBase64EncodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_LEFT_ENDPOINT_SUFFIX);
    }

    @Given("I save a right value using a not base64 encoded value like {string}")
    public void saveARightValueUsingANotBase64EncodedValueLike(String notBase64EncodedValue) {

        stubFor(post(urlEqualTo(DECODER_SERVICE_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(DECODER_SERVICE_OK_RESPONSE)));

        final JsonObject mainJson = new JsonObject();

        mainJson.addProperty(ENCODED_VALUE_FIELD_NAME, notBase64EncodedValue);
        addToPayload(mainJson.toString());

        executePost(DATA_DIFF_SERVICE_BASE_API_URL + DIFF_RECORD_ID + DATA_DIFF_SERVICE_RIGHT_ENDPOINT_SUFFIX);
    }

}

