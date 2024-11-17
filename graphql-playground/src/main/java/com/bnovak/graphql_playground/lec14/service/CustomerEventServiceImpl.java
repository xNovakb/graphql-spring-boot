package com.bnovak.graphql_playground.lec14.service;

import com.bnovak.graphql_playground.lec14.dto.CustomerEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class CustomerEventServiceImpl implements CustomerEventService {

    private final Sinks.Many<CustomerEvent> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final Flux<CustomerEvent> flux = sink.asFlux().cache(0);

    @Override
    public void emitEvent(CustomerEvent event) {
        this.sink.tryEmitNext(event);
    }

    @Override
    public Flux<CustomerEvent> subscribe() {
        return this.flux;
    }
}
