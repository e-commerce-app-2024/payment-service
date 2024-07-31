package com.ecommerce.app.config;

import com.ecommerce.app.enums.KafkaTopicName;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    @Bean
    public NewTopic createPaymentTopic() {
        return TopicBuilder
                .name(KafkaTopicName.PAYMENT)
                .build();
    }
}
