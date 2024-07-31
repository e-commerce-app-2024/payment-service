package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentConfirmation;
import com.ecommerce.app.dto.PaymentFilterRequest;
import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.exception.PaymentNotFoundException;
import com.ecommerce.app.integration.customer.adapter.CustomerAdapter;
import com.ecommerce.app.integration.customer.model.CustomerResponse;
import com.ecommerce.app.kafka.PaymentProducerService;
import com.ecommerce.app.mapper.PaymentMapper;
import com.ecommerce.app.model.PaymentEntity;
import com.ecommerce.app.payload.PageResponse;
import com.ecommerce.app.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;
    private final CustomerAdapter customerAdapter;
    private final PaymentProducerService paymentProducerService;
    private final BankingService bankingService;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        PaymentEntity payment = paymentMapper.toPayment(paymentRequest);
        payment.setReference(UUID.randomUUID().toString());
        boolean success = bankingService.Pay(paymentRequest);
        payment.setSuccess(success);
        payment = paymentRepo.save(payment);
        if (success)
            paymentProducerService.sendPaymentConfirmation(preparePaymentConfirmation(paymentRequest, payment.getReference()));
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PageResponse<PaymentResponse> getPayments(PaymentFilterRequest request) {
        Sort sort = Sort.by(request.sort() != null ? request.sort() : Sort.Direction.DESC, request.sortBy() != null ? request.sortBy() : "createdAt");
        PageRequest pageRequest = PageRequest.of(request.index().intValue(), request.size().intValue(), sort);
        Page<PaymentEntity> all = request.success() != null ? paymentRepo.findBySuccess(request.success(), pageRequest) : paymentRepo.findAll(pageRequest);
        return new PageResponse<>
                (paymentMapper.toPaymentResponse(all.getContent()),
                        all.isLast(),
                        all.getNumber(),
                        all.getSize(),
                        all.getTotalElements(),
                        all.getTotalPages());
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return paymentRepo.findById(id)
                .map(paymentMapper::toPaymentResponse)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("No Payment found with ID {}", id)));
    }

    @Override
    public List<PaymentResponse> getPaymentByOrderId(Long id) {
        List<PaymentEntity> all = paymentRepo.findByOrderId(id);
        return paymentMapper.toPaymentResponse(all);
    }

    private PaymentConfirmation preparePaymentConfirmation(PaymentRequest paymentRequest, String paymentReference) {
        CustomerResponse customer = customerAdapter.findCustomer(paymentRequest.customerId());
        return PaymentConfirmation.builder()
                .customerEmail(customer.email())
                .customerName(customer.firstName() + " " + customer.lastName())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .paymentReference(paymentReference)
                .orderReference(paymentRequest.orderReference())
                .build();
    }
}
