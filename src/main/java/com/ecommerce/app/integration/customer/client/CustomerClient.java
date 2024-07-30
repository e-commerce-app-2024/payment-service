package com.ecommerce.app.integration.customer.client;

import com.ecommerce.app.integration.customer.model.CustomerResponse;
import com.ecommerce.app.payload.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-service",
        url = "${spring.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{id}")
    AppResponse<CustomerResponse> findCustomer(@PathVariable String id);

    @GetMapping("/exists/{id}")
    AppResponse<Boolean> exists(@PathVariable String id);
}
