package com.bnovak.graphql_playground.sec01.lec02.dto;

import lombok.Data;

@Data
public class AgeRangeFilterDto {

    private Integer minAge;
    private Integer maxAge;

}
