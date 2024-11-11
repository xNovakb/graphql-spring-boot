package com.bnovak.graphql_playground.lec09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AllTypes {

    private UUID id;
    private Integer height;
    private Float temperature;
    private String city;
    private Boolean isValid;

    private Long distance;
    private Byte ageInYears;
    private Short ageInMonths;
    private BigDecimal bigDecimal;
    private BigInteger bigInteger;
    private LocalDate date;
    private LocalTime time;
    private OffsetDateTime dateTime;
    private CarEnum car;

}
