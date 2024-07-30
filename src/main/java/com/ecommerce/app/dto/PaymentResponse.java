package com.ecommerce.app.dto;

import com.ecommerce.app.enums.PaymentMethodEnum;

import java.math.BigDecimal;

public record PaymentResponse(
        String customerId,
        Long orderId,
        String reference,
        BigDecimal amount,
        PaymentMethodEnum paymentMethod,
        Boolean success
) {
}
