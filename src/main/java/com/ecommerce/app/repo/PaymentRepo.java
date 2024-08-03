package com.ecommerce.app.repo;

import com.ecommerce.app.model.PaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends BaseRepo<PaymentEntity> {

    Page<PaymentEntity> findBySuccess(Boolean success, Pageable pageable);

    List<PaymentEntity> findByOrderReference(String orderReference);

}
