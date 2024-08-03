package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankingServiceImpl implements BankingService {

    @Override
    public boolean Pay(PaymentRequest paymentRequest) {
        paymentRequest.amount().setScale(2, BigDecimal.ROUND_HALF_UP);
        return randomBoolean();
    }

    public boolean randomBoolean() {
        return Math.random() < 0.5;
    }
}
