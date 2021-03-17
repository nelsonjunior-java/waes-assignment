package com.waes.assignment.bdd.steps;

import io.cucumber.java.en.Given;

import static com.waes.assignment.utils.ServicesConstants.DATA_DIFF_SERVICE_BASE_API_URL;

public class ComparissonDataDiffStep extends AbstractStep{

    @Given("I compare a left and a right value using a non numerical id value as {string}")
    public void compareALeftAndARightValueUsingANonNumericalIdValue(String nonNumericalValue) {

        executeGet(DATA_DIFF_SERVICE_BASE_API_URL + nonNumericalValue);
    }

    @Given("I compare a left and a right value using an id that does not exist like {string}")
    public void compareALeftAndARightValueUsingAnIdThatDoesNotExist(String inexistentId) {

        executeGet(DATA_DIFF_SERVICE_BASE_API_URL + inexistentId);
    }

    @Given("I try to compare it in the comparison endpoint using the previous id {long}")
    public void tryToCompareItInTheComparisonEndpointUsingThePreviousId(Long id) {

        executeGet(DATA_DIFF_SERVICE_BASE_API_URL + id);
    }

}
