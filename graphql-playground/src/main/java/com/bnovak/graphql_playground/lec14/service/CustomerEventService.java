package com.bnovak.graphql_playground.lec14.service;

import com.bnovak.graphql_playground.lec14.dto.CustomerEvent;
import reactor.core.publisher.Flux;

public interface CustomerEventService {

    Flux<CustomerEvent> subscribe();

    void emitEvent(CustomerEvent event);

}
