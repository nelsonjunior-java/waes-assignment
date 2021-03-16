package com.waes.assignment.bdd;

import io.cucumber.java.After;
import lombok.extern.slf4j.Slf4j;

/** *
 * Default Hooks for running cucumber scenarios
 */
@Slf4j
public class CucumberDefaultHooks {

    /**
     * Clean up the context after running each scenario
     */
    @After
    public void defaultAfterHooks() {
        log.info("############# Cleaning Cucumber Context Test #############");
        CucumberContextTest.INSTANCE.reset();
    }
}
