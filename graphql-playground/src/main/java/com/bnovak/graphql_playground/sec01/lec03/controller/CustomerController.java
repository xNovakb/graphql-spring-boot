package com.bnovak.graphql_playground.sec01.lec03.controller;

import com.bnovak.graphql_playground.sec01.lec03.dto.CustomerDto;
import com.bnovak.graphql_playground.sec01.lec03.dto.CustomerOrderDto;
import com.bnovak.graphql_playground.sec01.lec03.service.CustomerService;
import com.bnovak.graphql_playground.sec01.lec03.service.OrderService;
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
    public Flux<CustomerOrderDto> getCustomerOrders(CustomerDto customer, @Argument Integer limit) {
        System.out.println("Orders method invoked for customer: " + customer.getName());
        return orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }

}
