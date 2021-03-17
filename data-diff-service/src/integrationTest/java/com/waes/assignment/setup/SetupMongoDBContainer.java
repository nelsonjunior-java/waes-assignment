package com.waes.assignment.setup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;


/**
 * This class is used to setup a MongoDB container
 * used in the integration tests
 */
@Slf4j
@TestConfiguration
public class SetupMongoDBContainer {


    final private static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
            .withExposedPorts(27017)
            .withClasspathResourceMapping(
                    "init-mongo.js",
                    "/docker-entrypoint-initdb.d/init-mongo.js:ro",
                    BindMode.READ_ONLY
            );


    static {
        log.info("[MONGODB-CONTAINER] ##### Creating Mongo Database Container  #####");

        mongoDBContainer.start();

        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

}
