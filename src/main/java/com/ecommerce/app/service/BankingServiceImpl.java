package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class BankingServiceImpl implements BankingService {

    @Override
    public boolean Pay(PaymentRequest paymentRequest) {
        return randomBoolean();
    }

    public boolean randomBoolean() {
        return Math.random() < 0.5;
    }
}
