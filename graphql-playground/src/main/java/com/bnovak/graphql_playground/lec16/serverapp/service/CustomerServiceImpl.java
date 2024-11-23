package com.bnovak.graphql_playground.lec16.serverapp.service;

import com.bnovak.graphql_playground.lec16.dto.Action;
import com.bnovak.graphql_playground.lec16.dto.CustomerDto;
import com.bnovak.graphql_playground.lec16.dto.CustomerEvent;
import com.bnovak.graphql_playground.lec16.dto.DeleteResponseDto;
import com.bnovak.graphql_playground.lec16.serverapp.repository.CustomerRepository;
import com.bnovak.graphql_playground.lec16.serverapp.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerEventService eventService;

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
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> eventService.emitEvent(CustomerEvent.create(c.getId(), Action.CREATED)));
    }

    @Override
    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(_ -> {
                    var customer = EntityDtoUtil.toEntity(customerDto);
                    customer.setId(id);
                    return customer;
                })
                .flatMap(customerRepository::save)
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> eventService.emitEvent(CustomerEvent.create(c.getId(), Action.UPDATED)));
    }

    @Override
    public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id)
                .doOnSuccess(_ -> eventService.emitEvent(CustomerEvent.create(id, Action.DELETED)))
                .thenReturn(DeleteResponseDto.create(id, Boolean.TRUE))
                .onErrorReturn(DeleteResponseDto.create(id, Boolean.FALSE));
    }
}
