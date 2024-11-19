package com.bnovak.graphql_playground.lec15.controller;

import com.bnovak.graphql_playground.lec15.dto.CustomerDto;
import com.bnovak.graphql_playground.lec15.dto.DeleteResponseDto;
import com.bnovak.graphql_playground.lec15.exception.ApplicationErrors;
import com.bnovak.graphql_playground.lec15.service.CustomerService;
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
    public Mono<CustomerDto> customerById(@Argument Integer id) {
        return customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationErrors.noSuchUser(id));
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument("customer") CustomerDto customerDto) {
        return Mono.just(customerDto)
                .filter(c -> c.getAge() >= 18)
                .flatMap(customerService::createCustomer)
                .switchIfEmpty(ApplicationErrors.ageIsLowerThan18(customerDto.getAge()));
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
