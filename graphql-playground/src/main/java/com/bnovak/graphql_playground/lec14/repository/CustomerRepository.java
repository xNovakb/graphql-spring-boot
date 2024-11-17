package com.bnovak.graphql_playground.lec14.repository;

import com.bnovak.graphql_playground.lec14.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
