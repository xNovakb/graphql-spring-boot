package com.bnovak.graphql_playground.lec15.service;

import com.bnovak.graphql_playground.lec15.dto.CustomerDto;
import com.bnovak.graphql_playground.lec15.dto.DeleteResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<CustomerDto> allCustomers();

    Mono<CustomerDto> getCustomerById(Integer id);

    Mono<CustomerDto> createCustomer(CustomerDto customerDto);

    Mono<CustomerDto> updateCustomer(Integer id, CustomerDto customerDto);

    Mono<DeleteResponseDto> deleteCustomer(Integer id);

}
