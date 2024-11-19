package com.bnovak.graphql_playground.lec15.exception;

import graphql.ErrorType;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ApplicationErrors {

    public static <T> Mono<T> noSuchUser(Integer id) {
        return Mono.error(new ApplicationException(
                ErrorType.DataFetchingException, "No such user with id: " + id, Map.of(
                "customerId", id
        )));
    }

}
