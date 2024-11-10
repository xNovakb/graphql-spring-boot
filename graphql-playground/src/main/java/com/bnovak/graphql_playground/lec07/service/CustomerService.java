package com.bnovak.graphql_playground.lec07.service;

import com.bnovak.graphql_playground.lec07.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
