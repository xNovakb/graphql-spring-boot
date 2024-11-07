package com.bnovak.graphql_playground.sec01.lec03.service;

import com.bnovak.graphql_playground.sec01.lec03.dto.CustomerOrderDto;
import reactor.core.publisher.Flux;

public interface OrderService {

    Flux<CustomerOrderDto> ordersByCustomerName(String name);

}
