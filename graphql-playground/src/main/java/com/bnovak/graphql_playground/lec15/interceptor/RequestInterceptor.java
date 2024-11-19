package com.bnovak.graphql_playground.lec15.interceptor;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RequestInterceptor implements WebGraphQlInterceptor {

    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        final var headers = request.getHeaders().getOrEmpty("caller-id");
        final var callerId = headers.isEmpty() ? "" : headers.getFirst();
        request.configureExecutionInput((e, b) -> b.graphQLContext(Map.of("callerId", callerId)).build());
        return chain.next(request);
    }

}
