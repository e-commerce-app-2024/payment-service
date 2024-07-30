package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);
}
