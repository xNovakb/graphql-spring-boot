package com.bnovak.graphql_playground.sec01.lec04.controller;

import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerOrderDto;
import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerDto;
import com.bnovak.graphql_playground.sec01.lec04.service.CustomerService;
import com.bnovak.graphql_playground.sec01.lec04.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @SchemaMapping(typeName = "Query", field = "customers")
    public Flux<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    //BatchMapping is used to fetch multiple orders for multiple customers in a single query solves the N+1 problem
/*    @BatchMapping(typeName = "Customer", field = "orders")
    public Flux<List<CustomerOrderDto>> getCustomerOrders(List<CustomerDto> customers) {
        System.out.println("Orders method invoked for customer: " + customers);
        return orderService.ordersByCustomerNames(customers.stream()
                .map(CustomerDto::getName)
                .collect(Collectors.toList()));
    }*/

    @BatchMapping(typeName = "Customer", field = "orders")
    public Mono<Map<CustomerDto, List<CustomerOrderDto>>> getCustomerOrders(List<CustomerDto> customers) {
        System.out.println("Orders method invoked for customer: " + customers);
        return orderService.fetchOrdersMap(customers);
    }

}
