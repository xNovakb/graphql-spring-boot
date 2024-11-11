package com.bnovak.graphql_playground.lec09.controller;

import com.bnovak.graphql_playground.lec09.dto.AllTypes;
import com.bnovak.graphql_playground.lec09.dto.CarEnum;
import com.bnovak.graphql_playground.lec09.dto.Product;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.Map;
import java.util.UUID;

@Controller
public class ScalarController {

    private final AllTypes allTypes = AllTypes.create(
            UUID.randomUUID(),
            180,
            36.6f,
            "New York",
            true,
            10000L,
            (byte) 25,
            (short) 300,
            new BigDecimal("12345.67"),
            new BigInteger("123456789"),
            LocalDate.now(),
            LocalTime.now(),
            OffsetDateTime.now(),
            CarEnum.BMW
    );

    @QueryMapping
    public Mono<AllTypes> get() {
        return Mono.just(this.allTypes);
    }

    @QueryMapping
    public Flux<Product> products() {
        return Flux.just(
                Product.create("Product 1", Map.of("attr1", "value1", "attr2", "value2")),
                Product.create("Product 2", Map.of("attr1", "value1", "attr2", "value2", "attr3", "value3")),
                Product.create("Product 3", Map.of("attr1", "value1"))
        );
    }

}
