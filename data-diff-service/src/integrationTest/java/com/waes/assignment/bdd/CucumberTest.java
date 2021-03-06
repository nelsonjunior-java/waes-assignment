package com.waes.assignment.bdd;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty"},
        glue = "com.waes.assignment.bdd")
public class CucumberTest {
}
