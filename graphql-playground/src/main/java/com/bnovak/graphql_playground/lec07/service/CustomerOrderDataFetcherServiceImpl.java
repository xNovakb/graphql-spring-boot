package com.bnovak.graphql_playground.lec07.service;

import com.bnovak.graphql_playground.lec07.dto.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class CustomerOrderDataFetcherServiceImpl implements DataFetcher<Flux<CustomerWithOrder>> {

    private final CustomerService customerService;

    private final OrderService orderService;

    @Override
    public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) throws Exception {
        var includeOrders = environment.getSelectionSet().contains("orders");
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
