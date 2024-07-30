package com.ecommerce.app.dto;

import com.ecommerce.app.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull(message = "Customer ID is required")
        String customerId,
        @NotNull(message = "Order ID is required")
        Long orderId,
        @NotNull(message = "Amount is required")
        BigDecimal amount,
        @NotNull(message = "The payment method is required")
        PaymentMethodEnum paymentMethod,
        String orderReference
) {
}
