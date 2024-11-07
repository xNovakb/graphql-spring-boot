package com.bnovak.graphql_playground.sec01.lec04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerOrderDto {

    private UUID id;
    private String description;

}
