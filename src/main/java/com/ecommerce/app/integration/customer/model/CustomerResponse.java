package com.ecommerce.app.integration.customer.model;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
