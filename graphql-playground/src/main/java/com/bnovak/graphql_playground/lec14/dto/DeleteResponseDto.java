package com.bnovak.graphql_playground.lec14.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class DeleteResponseDto {

    private Integer id;
    private Boolean success;

}
