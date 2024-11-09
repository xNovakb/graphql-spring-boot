package com.bnovak.graphql_playground.sec01.lec04.service;

import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerDto;
import com.bnovak.graphql_playground.sec01.lec04.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

    @Override
    public Flux<List<CustomerOrderDto>> ordersByCustomerNames(List<String> names) {
        return Flux.fromIterable(names)
                .flatMapSequential(name -> fetchOrders(name).defaultIfEmpty(Collections.emptyList()));
    }

    @Override
    public Mono<Map<CustomerDto, List<CustomerOrderDto>>> fetchOrdersMap(List<CustomerDto> customers) {
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, mapOfOrders.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(Tuple2::getT1, Tuple2::getT2);
    }

    private Mono<List<CustomerOrderDto>> fetchOrders(String name) {
        return Mono.justOrEmpty(mapOfOrders.get(name));
    }

}
