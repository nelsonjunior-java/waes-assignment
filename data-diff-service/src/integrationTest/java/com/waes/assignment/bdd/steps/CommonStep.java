package com.waes.assignment.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.pt.Entao;

/**
 * This class has the common steps that can be reused throughout the integration tests
 */
public class CommonStep extends AbstractStep {

    @Then("the operation is well succeed returning the http code 200")
    public void operaionSucceed() {
        validateResponseCode(200, 201);
    }

    @Then("the operation fails returning the http code 400")
    public void theOperationFailsReturningTheHttpCodeFourHundred() {
        validateResponseCode(400);
    }

    @Then("the operation fails returning the NOT FOUND http code 404")
    public void theOperationFailsReturningTheHttpCodeNotFound() {
        validateResponseCode(404);
    }

    @Entao("^the response contains the text (.*)$")
    public void responseHasTheText(String texto) {
        validatesResponseBodyContains(texto);
    }
}
