package com.waes.assignment.bdd.steps;

import com.waes.assignment.bdd.CucumberContextTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Abstract class inherited by all step definitions in order to use
 * the default behavior for using the tests API
 */
public class AbstractStep {

    @LocalServerPort
    private int port;

    private CucumberContextTest CONTEXT = CucumberContextTest.INSTANCE;

    /**
     * Adds a new path param to the request
     * @param json - payload JSON
     */
    protected void addToPayload(String json) {
        CONTEXT.setPayload(json);
    }

    /**
     * Creates a POST request with the context information
     * @param path - API Path that will be used in the request
     */
    protected void executePost(String path) {
        final var request = createDefaultRequest(CONTEXT.getHeaders(), CONTEXT.getPathParams(), CONTEXT.getQueryParams(), CONTEXT.getPayload());
        final var url = createEndpointUrl(path);

        final var response = request.log()
                .all()
                .post(url);

        logResponse(response);
        CONTEXT.setResponse(response);
    }

    /**
     * Creates a default Request for using on any RestAssured Request
     * @param headers  - Request Headers
     * @param pathParams  - Endpoint Path params
     * @param queryParams - Endpoint Query params
     * @param payload     - Request JSON payload
     * @return New request with the default information and the request parameters
     */
    private RequestSpecification createDefaultRequest(Map<String, String> headers, Map<String, String> pathParams, Map<String, String> queryParams, Object payload) {
        final var request = CONTEXT.getRequest();

        if (!CollectionUtils.isEmpty(headers)) {
            request.headers(headers);
        }

        if (!CollectionUtils.isEmpty(pathParams)) {
            request.pathParams(pathParams);
        }

        if (!CollectionUtils.isEmpty(queryParams)) {
            request.queryParams(queryParams);
        }

        if (payload != null) {
            request.contentType(ContentType.JSON)
                    .body(payload);
        }

        return request;
    }

    /**
     * Creates the endpoint URL from a base URL + request path
     * @param path - Endpoint path
     * @return Endpoint with base URL and API path
     */
    private String createEndpointUrl(String path) {
        return baseUrl() + path;
    }

    /**
     * Creates the base URL for using the API
     */
    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    /**
     * Logs the information returned on the API response
     * @param response - RestAssured response Object
     */
    private void logResponse(Response response) {
        response.then()
                .log()
                .all();
    }

    /**
     * Validates the returned status code
     * @param status - Status code
     */
    protected void validateResponseCode(int... status) {
        Assertions.assertThat(status).contains(CONTEXT.getResponse().getStatusCode());
    }

    /**
     * Validates if the message body has a given text
     * @param text - Text to be analyzed
     */
    protected void validatesResponseBodyContains(String text) {
        CONTEXT.getResponse().then().body(containsString(text));
    }
}
