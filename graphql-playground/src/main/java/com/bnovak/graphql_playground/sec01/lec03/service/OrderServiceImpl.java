package com.bnovak.graphql_playground.sec01.lec03.service;

import com.bnovak.graphql_playground.sec01.lec03.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<String, List<CustomerOrderDto>> mapOfOrders = Map.of(
            "John Doe", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "john's order 1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "john's order 2")
            ),
            "Jane Doe", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "Jane's order 1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "Jane's order 2"),
                    CustomerOrderDto.create(UUID.randomUUID(), "Jane's order 3")
            )
    );

    @Override
    public Flux<CustomerOrderDto> ordersByCustomerName(String name) {
        return Flux.fromIterable(mapOfOrders.getOrDefault(name, Collections.emptyList()));
    }
}
