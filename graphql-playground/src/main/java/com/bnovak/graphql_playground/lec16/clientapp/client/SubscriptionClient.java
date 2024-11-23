package com.bnovak.graphql_playground.lec16.clientapp.client;

import com.bnovak.graphql_playground.lec16.dto.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

@Service
public class SubscriptionClient {

    private final WebSocketGraphQlClient client;

    public SubscriptionClient(@Value("${customer.events.subscription.url}") final String baseUrl) {
        this.client = WebSocketGraphQlClient.builder(baseUrl, new ReactorNettyWebSocketClient()).build();
    }

    public Flux<CustomerEvent> customerEvents() {
        var doc = """
                subscription {
                    customerEvents {
                        id
                        action
                    }
                }
                """;
        return this.client.document(doc)
                .retrieveSubscription("customerEvents")
                .toEntity(CustomerEvent.class);
    }

}
