package com.example.webflux.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableReactiveMongoRepositories(basePackages = "com.example.webflux.dao")
public class MongoDbConfig extends AbstractReactiveMongoConfiguration {

    private final MongoProperties mongoProperties;

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getUri()))

//                .credential(MongoCredential.createCredential(
//                        mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword()))
                .retryWrites(true)
                .readConcern(ReadConcern.MAJORITY)
                .writeConcern(WriteConcern.MAJORITY)
                .build();

        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    public ReactiveMongoTransactionManager reactiveMongoTransactionManager(
            ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory
    ) {
        return new ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory);
    }
}
