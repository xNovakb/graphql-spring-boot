package com.bnovak.graphql_playground.lec03.service;

import com.bnovak.graphql_playground.lec03.dto.CustomerOrderDto;
import reactor.core.publisher.Flux;

public interface OrderService {

    Flux<CustomerOrderDto> ordersByCustomerName(String name);

}
