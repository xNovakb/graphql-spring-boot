package com.bnovak.graphql_playground.lec03.service;

import com.bnovak.graphql_playground.lec03.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
