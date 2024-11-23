package com.bnovak.graphql_playground;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class GraphqlCrudTest {

	@Autowired
	private HttpGraphQlTester client;

	@Test
	public void allCustomerTest() {
		final var doc = """
			query {
				customers {
					name
					age
				}
			}
			""";
		this.client.document(doc)
				.execute()
				.path("customers").entityList(Object.class).hasSizeGreaterThan(2)
				.path("customers.[0].name").entity(String.class).isEqualTo("Alice");
	}

}
