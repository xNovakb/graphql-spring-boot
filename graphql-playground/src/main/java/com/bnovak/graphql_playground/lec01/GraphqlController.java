package com.bnovak.graphql_playground.lec01;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class GraphqlController {

    @QueryMapping("sayHello")
    public Mono<String> sayHello() {
        return Mono.just("Hello, World!");
    }

    @QueryMapping("sayHelloTo")
    public Mono<String> sayHelloTo(@Argument("name") String name) {
        return Mono.fromSupplier(() -> "Hello, " + name + "!");
    }

    @QueryMapping()
    public Mono<Integer> random() {
        return Mono.just(ThreadLocalRandom.current().nextInt(1, 100));
    }

}
