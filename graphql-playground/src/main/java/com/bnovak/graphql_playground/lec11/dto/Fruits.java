package com.bnovak.graphql_playground.lec11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Fruits {

    private String description;
    private LocalDate expiryDate;

}
