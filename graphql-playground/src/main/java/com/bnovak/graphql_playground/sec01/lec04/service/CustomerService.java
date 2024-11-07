package com.bnovak.graphql_playground.sec01.lec04.service;

import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerDto;
import reactor.core.publisher.Flux;

public interface CustomerService {

    Flux<CustomerDto> getCustomers();

}
