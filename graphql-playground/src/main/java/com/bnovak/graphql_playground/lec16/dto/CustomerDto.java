package com.bnovak.graphql_playground.lec16.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements CustomerResponse {

    private Integer id;
    private String name;
    private Integer age;
    private String city;

}
