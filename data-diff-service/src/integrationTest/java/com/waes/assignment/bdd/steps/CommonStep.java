package com.waes.assignment.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.pt.Entao;

public class CommonStep extends AbstractStep{

    @Then("the operation is well succeed returning a http code 200")
    public void operaionSucceed() {
        validateResponseCode(200,201);
    }

    @Entao("^the response contains the text (.*)$")
    public void responseHasTheText(String texto) {
        validatesResponseBodyContains(texto);
    }
}
