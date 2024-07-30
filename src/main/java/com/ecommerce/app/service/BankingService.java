package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentRequest;

public interface BankingService {

    boolean Pay(PaymentRequest paymentRequest);
}
