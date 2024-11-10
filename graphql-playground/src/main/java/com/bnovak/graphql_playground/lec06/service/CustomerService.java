package com.bnovak.graphql_playground.lec06.service;

import com.bnovak.graphql_playground.lec06.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
