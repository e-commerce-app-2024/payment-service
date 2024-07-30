package com.ecommerce.app.controller;


import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.payload.AppResponse;
import com.ecommerce.app.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public AppResponse<PaymentResponse> createOrder(@Valid @RequestBody PaymentRequest request) {
        return AppResponse.created(paymentService.createPayment(request));
    }
}
