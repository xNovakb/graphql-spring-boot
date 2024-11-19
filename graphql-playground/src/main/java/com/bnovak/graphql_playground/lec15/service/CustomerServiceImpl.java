package com.bnovak.graphql_playground.lec15.service;

import com.bnovak.graphql_playground.lec15.dto.CustomerDto;
import com.bnovak.graphql_playground.lec15.dto.DeleteResponseDto;
import com.bnovak.graphql_playground.lec15.repository.CustomerRepository;
import com.bnovak.graphql_playground.lec15.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<CustomerDto> allCustomers() {
        return customerRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<CustomerDto> getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<CustomerDto> createCustomer(CustomerDto customerDto) {
        return Mono.just(customerDto)
                .map(EntityDtoUtil::toEntity)
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(customerDto))
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id)
                .thenReturn(DeleteResponseDto.create(id, Boolean.TRUE));
    }
}
