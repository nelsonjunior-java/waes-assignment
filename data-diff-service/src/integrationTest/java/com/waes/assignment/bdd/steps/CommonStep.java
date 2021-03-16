package com.waes.assignment.bdd.steps;

import io.cucumber.java.en.Then;

public class CommonStep extends AbstractStep{

    @Then("the operation is well succeed returning a http code 200")
    public void aOperacaoERealizadaComSucesso() {
        validateResponseCode(200,201);
    }
}
