package com.ecommerce.app.dto;

import com.ecommerce.app.enums.PaymentMethodEnum;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentConfirmation(
        String paymentReference,
        String orderReference,
        BigDecimal amount,
        PaymentMethodEnum paymentMethod,
        String customerName,
        String customerEmail
) {
}
