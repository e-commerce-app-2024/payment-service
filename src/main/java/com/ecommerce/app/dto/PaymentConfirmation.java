package com.ecommerce.app.dto;

import com.ecommerce.app.enums.PaymentMethodEnum;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String paymentReference,
        BigDecimal amount,
        PaymentMethodEnum PaymentMethod,
        String customerName,
        String customerEmail
) {
}
