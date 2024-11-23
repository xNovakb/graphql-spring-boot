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
        createCustomer()
                .then(getCustomerByIdCrud())
                .then(updateCustomer())
                .then(deleteCustomer())
                .then(getAllCustomers())
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

        return this.executor("Raw Query", mono);
    }

    private Mono<Void> getCustomerById() {
        return this.executor("getCustomerById", this.client.getCustomerByIdWithUnion(1));
    }

    private Mono<Void> getAllCustomers() {
        return this.executor("GetAll", this.client.allCustomers());
    }

    private Mono<Void> getCustomerByIdCrud() {
        return this.executor("GetCustomerById", this.client.getCustomerByIdCrud(11));
    }

    private Mono<Void> createCustomer() {
        return this.executor("CreateCustomer", this.client.createCustomer(new CustomerDto().setAge(30).setCity("Budapest").setName("John")));
    }

    private Mono<Void> updateCustomer() {
        return this.executor("UpdateCustomer", this.client.updateCustomer(2, new CustomerDto().setAge(18).setCity("Budapest").setName("John")));
    }

    private Mono<Void> deleteCustomer() {
        return this.executor("UpdateCustomer", this.client.deleteCustomer(11));
    }

    private <T> Mono<Void> executor(String message, Mono<T> mono) {
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println(message))
                .then(mono)
                .doOnNext(System.out::println)
                .then();
    }

}
