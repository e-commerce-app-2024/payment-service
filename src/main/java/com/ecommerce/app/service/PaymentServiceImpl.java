package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentConfirmation;
import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.integration.customer.adapter.CustomerAdapter;
import com.ecommerce.app.integration.customer.model.CustomerResponse;
import com.ecommerce.app.kafka.PaymentProducerService;
import com.ecommerce.app.mapper.PaymentMapper;
import com.ecommerce.app.model.PaymentEntity;
import com.ecommerce.app.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;
    private final CustomerAdapter customerAdapter;
    private final PaymentProducerService paymentProducerService;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        PaymentEntity payment = paymentMapper.toPayment(paymentRequest);
        payment.setReference(UUID.randomUUID().toString());
        payment = paymentRepo.save(payment);
        paymentProducerService.sendPaymentConfirmation(preparePaymentConfirmation(paymentRequest, payment.getReference()));
        return paymentMapper.toPaymentResponse(payment);
    }

    private PaymentConfirmation preparePaymentConfirmation(PaymentRequest paymentRequest, String paymentReference) {
        CustomerResponse customer = customerAdapter.findCustomer(paymentRequest.customerId());
        PaymentConfirmation paymentConfirmation = PaymentConfirmation.builder()
                .customerEmail(customer.email())
                .customerName(customer.firstName() + " " + customer.lastName())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .paymentReference(paymentReference)
                .build();
        return paymentConfirmation;
    }
}
