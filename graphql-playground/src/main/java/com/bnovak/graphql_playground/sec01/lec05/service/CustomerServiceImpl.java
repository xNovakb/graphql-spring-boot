package com.bnovak.graphql_playground.sec01.lec05.service;

import com.bnovak.graphql_playground.sec01.lec05.dto.CustomerDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Flux<CustomerDto> customers = Flux.just(
            CustomerDto.create(1, "John Doe", 30),
            CustomerDto.create(2, "Jane Doe", 25),
            CustomerDto.create(3, "Jack Doe", 35)
    );

    @Override
    public Flux<CustomerDto> getCustomers() {
        return customers;
    }

}
