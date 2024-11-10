package com.bnovak.graphql_playground.lec02.controller;

import com.bnovak.graphql_playground.lec02.dto.AgeRangeFilterDto;
import com.bnovak.graphql_playground.lec02.dto.CustomerDto;
import com.bnovak.graphql_playground.lec02.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping("customers")
    public Flux<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @QueryMapping("customerById")
    public Mono<CustomerDto> getCustomersById(@Argument Integer id) {
        return customerService.getCustomersById(id);
    }

    @QueryMapping("customerNameContains")
    public Flux<CustomerDto> getCustomerByNameContains(@Argument String name) {
        return customerService.getCustomerByNameContains(name);
    }

    @QueryMapping("customerByAgeRange")
    public Flux<CustomerDto> getCustomerByAgeInRange(@Argument AgeRangeFilterDto filter) {
        return customerService.getCustomerWithinAge(filter);
    }

}
