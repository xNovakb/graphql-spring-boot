package com.bnovak.graphql_playground.sec01.lec05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AddressDto {

    private String street;
    private String city;

}
