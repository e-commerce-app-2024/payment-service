package com.ecommerce.app.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;

public record PaymentFilterRequest(

        @NotNull(message = "Page index is required")
        Long index,
        @NotNull(message = "Page size is required")
        Long size,
        Boolean success,
        String sortBy,
        Sort.Direction sort
) {
}
