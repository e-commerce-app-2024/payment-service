package com.ecommerce.app.kafka;

import com.ecommerce.app.dto.PaymentConfirmation;
import com.ecommerce.app.enums.KafkaTopicName;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentProducerServiceImpl implements PaymentProducerService {

    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    @Override
    public void sendPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("send payment confirmation with body: <{}>", paymentConfirmation);
        try {
            Message message = MessageBuilder
                    .withPayload(paymentConfirmation)
                    .setHeader(KafkaHeaders.TOPIC, KafkaTopicName.PAYMENT).build();
            kafkaTemplate.send(message);
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}
