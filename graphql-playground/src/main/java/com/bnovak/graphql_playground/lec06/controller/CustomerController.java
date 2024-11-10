package com.bnovak.graphql_playground.lec06.controller;

import com.bnovak.graphql_playground.lec06.dto.CustomerDto;
import com.bnovak.graphql_playground.lec06.dto.CustomerOrderDto;
import com.bnovak.graphql_playground.lec06.service.CustomerService;
import com.bnovak.graphql_playground.lec06.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @SchemaMapping(typeName = "Query", field = "customers")
    public Flux<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @SchemaMapping(typeName = "Customer", field = "orders")
    public Flux<CustomerOrderDto> getCustomerOrders(CustomerDto customer) {
        return orderService.ordersByCustomerName(customer.getName());
    }

}
