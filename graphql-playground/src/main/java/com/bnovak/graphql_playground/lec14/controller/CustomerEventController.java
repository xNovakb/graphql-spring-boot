package com.bnovak.graphql_playground.lec14.controller;

import com.bnovak.graphql_playground.lec14.dto.CustomerEvent;
import com.bnovak.graphql_playground.lec14.service.CustomerEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerEventController {

    private final CustomerEventService eventService;

    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents() {
        return eventService.subscribe();
    }

}
