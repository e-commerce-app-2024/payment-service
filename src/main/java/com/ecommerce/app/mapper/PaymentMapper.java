package com.ecommerce.app.mapper;


import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.dto.PaymentResponse;
import com.ecommerce.app.model.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {

    PaymentEntity toPayment(PaymentRequest paymentRequest);

    PaymentResponse toPaymentResponse(PaymentEntity paymentEntity);

    List<PaymentResponse> toPaymentResponse(List<PaymentEntity> list);
}

