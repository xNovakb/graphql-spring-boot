package com.bnovak.graphql_playground.lec10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Electronics {

    private final UUID id = UUID.randomUUID();
    private String description;
    private Integer price;
    private String brand;

}
