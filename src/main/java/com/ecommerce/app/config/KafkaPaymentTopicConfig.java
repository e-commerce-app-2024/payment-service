package com.ecommerce.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    //@Value("${app.num.partitions}")
    //private int numPartitions;
    //@Value("${app.num.replicationFactor}")
    //private short replicationFactor;

    @Bean
    public NewTopic topicMCC() {
        //NewTopic order = new NewTopic("payment", numPartitions, replicationFactor);
        return TopicBuilder
                .name("payment")
                .build();
    }
}
