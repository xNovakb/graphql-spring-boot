package com.bnovak.graphql_playground.lec06.service;

import com.bnovak.graphql_playground.lec06.dto.CustomerWithOrder;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class CustomerOrderDataFetcherServiceImpl {

    private final CustomerService customerService;

    private final OrderService orderService;

    public Flux<CustomerWithOrder> customerOrders(DataFetchingFieldSelectionSet selectionSet) {
        var includeOrders = selectionSet.contains("orders");
        return customerService.getCustomers()
                .map(c -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), Collections.emptyList()))
                .transform(updateOrdersTransformer(includeOrders));
    }

    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders) {
        return includeOrders ? f -> f.flatMapSequential(this::fetchOrders) : f -> f;
    }


    private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customerWithOrder) {
        return orderService.ordersByCustomerName(customerWithOrder.getName())
                .collectList()
                .doOnNext(customerWithOrder::setOrders)
                .thenReturn(customerWithOrder);
    }

}
