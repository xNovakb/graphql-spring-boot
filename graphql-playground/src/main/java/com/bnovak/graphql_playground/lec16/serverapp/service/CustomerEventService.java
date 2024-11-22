package com.bnovak.graphql_playground.lec16.serverapp.service;

import com.bnovak.graphql_playground.lec16.dto.CustomerEvent;
import reactor.core.publisher.Flux;

public interface CustomerEventService {

    Flux<CustomerEvent> subscribe();

    void emitEvent(CustomerEvent event);

}
