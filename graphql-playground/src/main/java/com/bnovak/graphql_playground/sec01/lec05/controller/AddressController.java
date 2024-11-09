package com.bnovak.graphql_playground.sec01.lec05.controller;

import com.bnovak.graphql_playground.sec01.lec05.dto.AddressDto;
import com.bnovak.graphql_playground.sec01.lec05.dto.CustomerDto;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<AddressDto> address(CustomerDto customer) {
        return Mono.just(
                AddressDto.create(customer.getName() + " street ", customer.getName() + " city")
        );
    }

}
