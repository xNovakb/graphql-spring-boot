package com.bnovak.graphql_playground.sec01.lec02.service;

import com.bnovak.graphql_playground.sec01.lec02.dto.AgeRangeFilterDto;
import com.bnovak.graphql_playground.sec01.lec02.dto.CustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

    Mono<CustomerDto> getCustomersById(Integer id);

    Flux<CustomerDto> getCustomerByNameContains(String name);

    Flux<CustomerDto> getCustomerWithinAge(AgeRangeFilterDto filter);

}
