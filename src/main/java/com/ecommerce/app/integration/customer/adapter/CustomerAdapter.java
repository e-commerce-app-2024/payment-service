package com.ecommerce.app.integration.customer.adapter;

import com.ecommerce.app.exception.CustomerIntegrationException;
import com.ecommerce.app.integration.customer.client.CustomerClient;
import com.ecommerce.app.integration.customer.model.CustomerResponse;
import com.ecommerce.app.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerAdapter {

    private final CustomerClient customerClient;

    public Boolean exists(String id) {
        AppResponse<Boolean> appResponse = customerClient.exists(id);
        if (appResponse.success()) {
            return appResponse.payload().booleanValue();
        } else {
            List<String> errors = appResponse.error().errors();
            throw new CustomerIntegrationException(errors.get(0));
        }
    }

    public CustomerResponse findCustomer(String id) {
        AppResponse<CustomerResponse> appResponse = customerClient.findCustomer(id);
        if (appResponse.success()) {
            return appResponse.payload();
        } else {
            List<String> errors = appResponse.error().errors();
            throw new CustomerIntegrationException(errors.get(0));
        }
    }

}
