package com.bnovak.graphql_playground.lec15.exception;

import graphql.ErrorType;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApplicationException extends  RuntimeException {

    private final ErrorType errorType;
    private final String message;
    private final Map<String, Object> extensions;

    public ApplicationException(ErrorType errorType, String message, Map<String, Object> extensions) {
        super(message);
        this.errorType = errorType;
        this.message = message;
        this.extensions = extensions;
    }

}
