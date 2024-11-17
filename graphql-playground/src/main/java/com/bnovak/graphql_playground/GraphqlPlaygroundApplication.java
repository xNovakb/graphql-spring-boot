package com.bnovak.graphql_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.bnovak.graphql_playground.lec14")
@EnableR2dbcRepositories(basePackages = "com.bnovak.graphql_playground.lec14")
public class GraphqlPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlaygroundApplication.class, args);
	}

}
