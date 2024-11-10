package com.bnovak.graphql_playground.lec06.controller;

import com.bnovak.graphql_playground.lec06.dto.CustomerWithOrder;
import com.bnovak.graphql_playground.lec06.service.CustomerOrderDataFetcherServiceImpl;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerOrderDataFetcherServiceImpl dataFetcher;

    @SchemaMapping(typeName = "Query", field = "customers")
    public Flux<CustomerWithOrder> getCustomers(DataFetchingFieldSelectionSet selectionSet) {
        return dataFetcher.customerOrders(selectionSet);
    }

}
