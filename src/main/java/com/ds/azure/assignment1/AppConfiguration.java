package com.ds.azure.assignment1;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableSwagger2
public class AppConfiguration {

    @Value("${blob.connection-string}")
    String connectionString;

    @Value("${blob.container-name}")
    String containerName;
    @Value("${queue-name}")
    String queueName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }



    @Bean
    public BlobClientBuilder getClient() {
        BlobClientBuilder client = new BlobClientBuilder();
        client.connectionString(connectionString);
        client.containerName(containerName);
        return client;
    }
    @Bean
    public QueueClient getQueueClient() {
        QueueClient queueClient = new QueueClientBuilder()
                .connectionString(connectionString)
                .queueName(queueName)
                .buildClient();
        return queueClient;
    }
}
