package com.waes.assignment.bdd;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

/**
 * Context to be used inside a cucumber scenario
 */
public enum CucumberContextTest {
    INSTANCE;

    private static final String PAYLOAD = "PAYLOAD";
    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";
    private static final String PATH_PARAMS = "PATH_PARAMS";
    private static final String QUERY_PARAMS = "QUERY_PARAMS";
    private static final String HEADERS = "HEADERS";


    private final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
        return threadLocal.get();
    }

    public void set(String key, Object value) {
        testContextMap().put(key, value);
    }

    public Object get(String key) {
        return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(testContextMap().get(key));
    }

    public void setPayload(Object value) {
        set(PAYLOAD, value);
    }

    public Object getPayload() {
        return testContextMap().get(PAYLOAD);
    }

    public <T> T getPayload(Class<T> clazz) {
        return get(PAYLOAD, clazz);
    }

    public RequestSpecification getRequest() {
        RequestSpecification req = get(REQUEST, RequestSpecification.class);
        return (null == req) ? given() : req;
    }

    public void setResponse(Response response) {
        set(RESPONSE, response);
    }

    public Response getResponse() {
        return get(RESPONSE, Response.class);
    }

    public void addPathParam(String key, String value) {

    }

    public Map<String, String> getQueryParams() {
        final var queryParamObj = get(QUERY_PARAMS);
        if (queryParamObj != null) {
            return (Map<String, String>) queryParamObj;
        } else {
            set(QUERY_PARAMS, new HashMap<>());
            return (Map<String, String>) get(QUERY_PARAMS);
        }
    }

    public Map<String, String> getPathParams() {
        final var pathParamObj = get(PATH_PARAMS);
        if (pathParamObj != null) {
            return (Map<String, String>) pathParamObj;
        } else {
            set(PATH_PARAMS, new HashMap<>());
            return (Map<String, String>) get(PATH_PARAMS);
        }
    }

    public void reset() {
        testContextMap().clear();
    }

    public Map<String, String> getHeaders() {
        final var headersObj = get(HEADERS);
        if (headersObj != null) {
            return (Map<String, String>) headersObj;
        } else {
            set(HEADERS, new HashMap<>());
            return (Map<String, String>) get(HEADERS);
        }
    }

    public String getHeader(String key) {
        final var headers = getHeaders();
        return headers.get(key);
    }

    public void setHeader(String key, String value) {
        final var headers = getHeaders();
        headers.put(key, value);
    }
}
