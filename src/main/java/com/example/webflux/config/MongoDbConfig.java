package com.example.webflux.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@Slf4j
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.example.webflux.dao")
public class MongoDbConfig extends AbstractReactiveMongoConfiguration {

    @Value("${mongo.connectionString}")
    private String connectionString;
    @Value("${mongo.database}")
    private String database;

    @Override
    public MongoClient reactiveMongoClient() {
        log.info("connectionString: {}", connectionString);
        return MongoClients.create(connectionString);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
