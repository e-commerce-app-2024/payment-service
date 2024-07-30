package com.ecommerce.app.kafka;

import com.ecommerce.app.dto.PaymentConfirmation;

public interface PaymentProducerService {

    void sendPaymentConfirmation(PaymentConfirmation paymentConfirmation);
}
