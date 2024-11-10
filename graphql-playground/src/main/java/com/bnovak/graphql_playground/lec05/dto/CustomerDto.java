package com.bnovak.graphql_playground.lec05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;

}
