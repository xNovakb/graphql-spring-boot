package com.bnovak.graphql_playground.sec01.lec04.service;

import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerDto;
import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerOrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Flux<CustomerOrderDto> ordersByCustomerName(String name);

    Flux<List<CustomerOrderDto>> ordersByCustomerNames(List<String> names);

    Mono<Map<CustomerDto, List<CustomerOrderDto>>> fetchOrdersMap(List<CustomerDto> customers);

}
