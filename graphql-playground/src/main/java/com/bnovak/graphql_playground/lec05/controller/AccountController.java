package com.bnovak.graphql_playground.lec05.controller;

import com.bnovak.graphql_playground.lec05.dto.AccountDto;
import com.bnovak.graphql_playground.lec05.dto.CustomerDto;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @SchemaMapping(typeName = "Customer")
    Mono<AccountDto> account(CustomerDto customer) {
        var type = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVINGS";

        return Mono.just(
                AccountDto.create(UUID.randomUUID(), ThreadLocalRandom.current().nextInt(1, 1000), type)
        );
    }
}
