package com.bnovak.graphql_playground.sec01.lec04.service;

import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerOrderDto;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrderService {

    Flux<CustomerOrderDto> ordersByCustomerName(String name);

    Flux<List<CustomerOrderDto>> ordersByCustomerNames(List<String> names);

}
