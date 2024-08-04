package com.ecommerce.app.controller;


import com.ecommerce.app.dto.PaymentFilterRequest;
import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.payload.AppResponse;
import com.ecommerce.app.payload.PageResponse;
import com.ecommerce.app.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public AppResponse<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
        return AppResponse.created(paymentService.createPayment(request));
    }

    @PostMapping("/filter")
    public AppResponse<PageResponse<PaymentResponse>> getPayments(@Valid @RequestBody PaymentFilterRequest request) {
        return AppResponse.ok(paymentService.getPayments(request));
    }

    @GetMapping("/{id}")
    public AppResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return AppResponse.ok(paymentService.getPaymentById(id));
    }

    @GetMapping
    public AppResponse<List<PaymentResponse>> getPaymentByOrder(@RequestParam @NotNull(message = "order reference is required") String orderReference) {
        return AppResponse.ok(paymentService.getPaymentByOrder(orderReference));
    }

}
