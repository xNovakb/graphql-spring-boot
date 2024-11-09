package com.bnovak.graphql_playground.sec01.lec05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AccountDto {

    private UUID id;
    private Integer amount;
    private String accountType;

}
