package com.bnovak.graphql_playground;

import com.bnovak.graphql_playground.lec16.dto.CustomerDto;
import com.bnovak.graphql_playground.lec16.dto.DeleteResponseDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

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

	@Test
	public void customerByIdTest() {
		this.client.documentName("crud-operations")
			.variable("id", 1)
			.operationName("GetCustomerById")
			.execute()
			.path("response.id").entity(Integer.class).isEqualTo(1)
			.path("response.name").entity(String.class).isEqualTo("Alice")
			.path("response.age").entity(Integer.class).isEqualTo(25)
			.path("response.city").entity(String.class).isEqualTo("New York");
	}

	@Test
	public void createCustomerTest() {
		this.client.documentName("crud-operations")
				.variable("customer", new CustomerDto().setName("Bob").setAge(30).setCity("Los Angeles"))
				.operationName("CreateCustomer")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(11)
				.path("response.name").entity(String.class).isEqualTo("Bob")
				.path("response.age").entity(Integer.class).isEqualTo(30)
				.path("response.city").entity(String.class).isEqualTo("Los Angeles");
	}

	@Test
	public void updateCustomerTest() {
		this.client.documentName("crud-operations")
				.variables(Map.of("id", 1, "customer", new CustomerDto().setName("Alice").setAge(35).setCity("New York")))
				.operationName("UpdateCustomer")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(1)
				.path("response.name").entity(String.class).isEqualTo("Alice")
				.path("response.age").entity(Integer.class).isEqualTo(35)
				.path("response.city").entity(String.class).isEqualTo("New York")
				.path("response").entity(Object.class).satisfies(System.out::println);
	}

	@Test
	public void deleteCustomerTest() {
		this.client.documentName("crud-operations")
				.variable("id", 10)
				.operationName("DeleteCustomer")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(10)
				.path("response.success").entity(Boolean.class).isEqualTo(true)
				.path("response").entity(DeleteResponseDto.class).satisfies(r -> {
					Assertions.assertThat(r.getId()).isEqualTo(10);
					Assertions.assertThat(r.getSuccess()).isTrue();
				});
	}

}
