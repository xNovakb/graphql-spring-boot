package com.bnovak.graphql_playground.lec16.serverapp.controller;

import com.bnovak.graphql_playground.lec16.dto.CustomerDto;
import com.bnovak.graphql_playground.lec16.dto.CustomerNotFound;
import com.bnovak.graphql_playground.lec16.dto.CustomerResponse;
import com.bnovak.graphql_playground.lec16.dto.DeleteResponseDto;
import com.bnovak.graphql_playground.lec16.serverapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<CustomerDto> customers() {
        return customerService.allCustomers();
    }

    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id) {
        return customerService.getCustomerById(id)
                .cast(Object.class)
                .defaultIfEmpty(CustomerNotFound.create(id));
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument("customer") CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
        return customerService.deleteCustomer(id);
    }

}
