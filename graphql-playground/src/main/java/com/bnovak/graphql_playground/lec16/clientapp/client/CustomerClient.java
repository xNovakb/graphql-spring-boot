package com.bnovak.graphql_playground.lec16.clientapp.client;

import com.bnovak.graphql_playground.lec16.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    public Mono<CustomerResponse> getCustomerByIdWithUnion(Integer id) {
        return this.client
                .documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .map(cr -> {
                    var field = cr.field("customerById");
                    var isCustomer = "Customer".equals(cr.field("customerById.type").getValue().toString());
                    return isCustomer ? field.toEntity(CustomerDto.class) : field.toEntity(CustomerNotFound.class);
                });
    }

    public Mono<List<CustomerDto>> allCustomers() {
        return this.crud("GetAll", Collections.emptyMap(), new ParameterizedTypeReference<List<CustomerDto>>() {});
    }

    public Mono<CustomerDto> getCustomerByIdCrud(Integer id) {
        return this.crud("GetCustomerById", Map.of("id", id), new ParameterizedTypeReference<CustomerDto>() {});
    }

    private <T> Mono<T> crud(String operationName, Map<String, Object> variables, ParameterizedTypeReference<T> type) {
        return this.client.documentName("crud-operations")
                .operationName(operationName)
                .variables(variables)
                .retrieve("response")
                .toEntity(type);
    }

}
