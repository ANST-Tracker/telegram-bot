package com.anst.sd.telegram.fw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestContainersConfig {
    public static final MongoDBContainer mongo = new MongoDBContainer(
            DockerImageName.parse("mongo:5"))
            .waitingFor(Wait.forListeningPort());

    static {
        mongo.start();
        System.setProperty("spring.data.mongodb.uri", mongo.getReplicaSetUrl());
    }

    @Bean(destroyMethod = "stop")
    public MongoDBContainer mongoDBContainer() {
        return mongo;
    }
}
