package com.bnovak.graphql_playground.lec10.controller;

import com.bnovak.graphql_playground.lec10.dto.Book;
import com.bnovak.graphql_playground.lec10.dto.Electronics;
import com.bnovak.graphql_playground.lec10.dto.Fruits;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products() {
        return Flux.just(
                Book.create("Book description", 100, "Book author"),
                Fruits.create("banana", 200, LocalDate.now()),
                Fruits.create("apple", 100, LocalDate.now()),
                Electronics.create("laptop", 1000, "APPLE"),
                Electronics.create("phone", 500, "SAMSUNG")
        );
    }

}
