package com.bnovak.graphql_playground.lec05.service;

import com.bnovak.graphql_playground.lec05.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
