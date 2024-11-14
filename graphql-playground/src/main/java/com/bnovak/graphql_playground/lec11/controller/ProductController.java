package com.bnovak.graphql_playground.lec11.controller;

import com.bnovak.graphql_playground.lec11.dto.Book;
import com.bnovak.graphql_playground.lec11.dto.Electronics;
import com.bnovak.graphql_playground.lec11.dto.Fruits;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Controller
public class ProductController {

    List<Object> list = List.of(
            Book.create("Book title", "Book author"),
            Fruits.create("banana", LocalDate.now()),
            Fruits.create("apple", LocalDate.now()),
            Electronics.create("laptop", 1000, "APPLE"),
            Electronics.create("phone", 500, "SAMSUNG")
    );

    @QueryMapping
    public Flux<Object> search() {
        return Mono.fromSupplier(() -> new ArrayList<>(list))
                .doOnNext(Collections::shuffle)
                .flatMapIterable(Function.identity())
                .take(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

}
