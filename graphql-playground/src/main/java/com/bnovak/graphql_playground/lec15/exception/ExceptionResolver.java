package com.bnovak.graphql_playground.lec15.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {

        final var ex  = toApplicationException(exception);
        return Mono.just(
             List.of(
                GraphqlErrorBuilder.newError()
                    .message(ex.getMessage())
                    .errorType(ex.getErrorType())
                    .extensions(ex.getExtensions())
                    .build()
             )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable) {
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable :
                new ApplicationException(ErrorType.ValidationError, throwable.getMessage(), Collections.emptyMap());
    }
}
