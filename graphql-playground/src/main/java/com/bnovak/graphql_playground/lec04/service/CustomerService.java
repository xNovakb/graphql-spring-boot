package com.bnovak.graphql_playground.lec04.service;

import com.bnovak.graphql_playground.lec04.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
