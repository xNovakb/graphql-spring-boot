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

    public static <T> Mono<T> ageIsLowerThan18(Integer age) {
        return Mono.error(new ApplicationException(
                ErrorType.DataFetchingException, "Age is lower than 18", Map.of(
                "age", age
        )));
    }

}
