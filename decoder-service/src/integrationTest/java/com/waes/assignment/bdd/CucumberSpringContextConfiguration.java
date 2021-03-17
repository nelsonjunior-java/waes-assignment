package com.waes.assignment.bdd;

import com.waes.assignment.DecoderServiceApplication;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = {
        DecoderServiceApplication.class,
},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringContextConfiguration {

    /**
     * Hook in order to cucumber recognize the classs as a glue and load the Spring context
     */
    @Before
    public void setUp() {
        log.info("############# Starting Spring Context for Cucumber Tests #############");
    }
}
