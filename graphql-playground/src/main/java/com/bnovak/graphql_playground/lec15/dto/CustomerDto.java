package com.bnovak.graphql_playground.lec15.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
    private String city;

}
