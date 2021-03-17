package com.waes.assignment.bdd;

import com.waes.assignment.DataDiffServiceApplication;
import com.waes.assignment.setup.SetupMongoDBContainer;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = {
        DataDiffServiceApplication.class,
        SetupMongoDBContainer.class,
},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWireMock(port = 9999, files = "file:src/integrationTest/resources")
public class CucumberSpringContextConfiguration {

    /**
     * Hook used to cucumber recognize the class as a glue and load the Spring context
     */
    @Before
    public void setUp() {
        log.info("############# Starting Spring Context for Cucumber Tests #############");
    }

}
