package com.ecommerce.app.model;


import com.ecommerce.app.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
@Entity(name = "PAYMENT")
public class PaymentEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_SEQ")
    @SequenceGenerator(name = "PAYMENT_SEQ", sequenceName = "PAYMENT_SEQ", allocationSize = 1)
    private Long id;
    private Long orderId;
    private String reference;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
    private boolean success;
}
