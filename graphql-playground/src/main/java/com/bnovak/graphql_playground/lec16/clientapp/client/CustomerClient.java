package com.bnovak.graphql_playground.lec16.clientapp.client;

import com.bnovak.graphql_playground.lec16.dto.CustomerDto;
import com.bnovak.graphql_playground.lec16.dto.GenericResponse;
import com.bnovak.graphql_playground.lec16.dto.MultiCustomerAssignment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer.service.url}") final String baseUrl) {
        this.client = HttpGraphQlClient.builder()
                .webClient(b -> b.baseUrl(baseUrl))
                .build();
    }

    public Mono<ClientGraphQlResponse> rawQuery(String query) {
        return this.client.document(query)
                .execute();
    }

    public Mono<GenericResponse<CustomerDto>> getCustomerById(Integer id) {
        return this.client
                .documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .map(cr -> {
                    var field = cr.field("customerById");
                    return Objects.nonNull(field.getValue()) ? new GenericResponse<>(field.toEntity(CustomerDto.class)) :
                            new GenericResponse<>(field.getErrors());
                });
    }

}
