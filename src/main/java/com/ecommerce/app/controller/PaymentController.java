package com.ecommerce.app.controller;


import com.ecommerce.app.dto.PaymentFilterRequest;
import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.payload.AppResponse;
import com.ecommerce.app.payload.PageResponse;
import com.ecommerce.app.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public AppResponse<PaymentResponse> createOrder(@Valid @RequestBody PaymentRequest request) {
        return AppResponse.created(paymentService.createPayment(request));
    }

    @PostMapping("/filter")
    public AppResponse<PageResponse<PaymentResponse>> getPayments(@Valid @RequestBody PaymentFilterRequest request) {
        return AppResponse.created(paymentService.getPayments(request));
    }

    @GetMapping("/{id}")
    public AppResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
        return AppResponse.created(paymentService.getPaymentById(id));
    }

    @GetMapping
    public AppResponse<List<PaymentResponse>> getPaymentByOrderId(@RequestParam @Positive(message = "order Id is invalid") Long orderId) {
        return AppResponse.created(paymentService.getPaymentByOrderId(orderId));
    }

}
