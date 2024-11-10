package com.bnovak.graphql_playground.lec03.service;

import com.bnovak.graphql_playground.lec03.dto.CustomerDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Flux<CustomerDto> customers = Flux.just(
            CustomerDto.create(1, "John Doe", 30, "New York"),
            CustomerDto.create(2, "Jane Doe", 25, "Los Angeles"),
            CustomerDto.create(3, "Jack Doe", 35, "Chicago")
    );

    @Override
    public Flux<CustomerDto> getCustomers() {
        return customers;
    }
}
