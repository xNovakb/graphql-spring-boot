package com.bnovak.graphql_playground.lec07.config;

import com.bnovak.graphql_playground.lec07.service.CustomerOrderDataFetcherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@RequiredArgsConstructor
public class DataFetcherWiringConfig {

    private final CustomerOrderDataFetcherServiceImpl dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return c -> c.type("Query", b -> b.dataFetcher("customers", dataFetcher));
    }

}
