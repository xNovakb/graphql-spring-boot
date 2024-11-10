package com.bnovak.graphql_playground.lec02.dto;

import lombok.Data;

@Data
public class AgeRangeFilterDto {

    private Integer minAge;
    private Integer maxAge;

}
