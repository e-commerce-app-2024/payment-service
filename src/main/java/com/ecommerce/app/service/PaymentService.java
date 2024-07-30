package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentFilterRequest;
import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.payload.PageResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PageResponse<PaymentResponse> getPayments(PaymentFilterRequest request);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getPaymentByOrderId(Long id);
}
