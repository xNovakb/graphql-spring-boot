package com.bnovak.graphql_playground.lec02.service;

import com.bnovak.graphql_playground.lec02.dto.AgeRangeFilterDto;
import com.bnovak.graphql_playground.lec02.dto.CustomerDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<CustomerDto> getCustomersById(Integer id) {
        return customers.filter(c -> c.getId().equals(id))
                .next();
    }

    @Override
    public Flux<CustomerDto> getCustomerByNameContains(String name) {
        return customers.filter(c -> c.getName().contains(name));
    }

    @Override
    public Flux<CustomerDto> getCustomerWithinAge(AgeRangeFilterDto filter) {
        return customers.filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
    }
}
