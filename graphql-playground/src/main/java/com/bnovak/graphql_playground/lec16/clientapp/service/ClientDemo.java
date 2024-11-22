package com.bnovak.graphql_playground.lec16.clientapp.service;

import com.bnovak.graphql_playground.lec16.clientapp.client.CustomerClient;
import com.bnovak.graphql_playground.lec16.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ClientDemo implements CommandLineRunner {

    private final CustomerClient client;

    @Override
    public void run(String... args) throws Exception {
        rawQueryDemo()
                .then(this.getCustomerById())
                .subscribe();
    }

    private Mono<Void> rawQueryDemo() {
        String query = """
                {
                    customers {
                        id
                        name
                        age
                        city
                    }
                }
                """;

        final var mono = this.client.rawQuery(query)
                .map(cr -> cr.field("customers").toEntityList(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println("Raw Query"))
                .then(mono)
                .doOnNext(System.out::println)
                .then();

    }

    private Mono<Void> getCustomerById() {
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println("getCustomerById"))
                .then(this.client.getCustomerById(1))
                .doOnNext(System.out::println)
                .then();
    }

}
