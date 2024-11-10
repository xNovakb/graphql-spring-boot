package com.bnovak.graphql_playground.lec05.controller;

import com.bnovak.graphql_playground.lec05.dto.CustomerDto;
import com.bnovak.graphql_playground.lec05.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping("customers")
    public Flux<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

}
