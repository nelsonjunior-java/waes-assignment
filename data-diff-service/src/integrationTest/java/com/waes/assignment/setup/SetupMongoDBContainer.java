package com.waes.assignment.setup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * This class is used to setup a MongoDB container
 * used in the integration tests
 */
@Slf4j
@TestConfiguration
public class SetupMongoDBContainer {

    final private static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    static {
        log.info("[MONGODB-CONTAINER] ##### Creating Mongo Container Database #####");

        mongoDBContainer.start();
    }


}
