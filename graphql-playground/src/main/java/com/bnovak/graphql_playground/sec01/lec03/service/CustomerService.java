package com.bnovak.graphql_playground.sec01.lec03.service;

import com.bnovak.graphql_playground.sec01.lec03.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
