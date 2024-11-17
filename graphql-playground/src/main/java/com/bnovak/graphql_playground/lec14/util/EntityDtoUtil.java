package com.bnovak.graphql_playground.lec14.util;

import com.bnovak.graphql_playground.lec14.dto.CustomerDto;
import com.bnovak.graphql_playground.lec14.entity.Customer;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static Customer toEntity(CustomerDto dto) {
        Customer entity = new Customer();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static CustomerDto toDto(Customer entity) {
        CustomerDto dto = new CustomerDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private EntityDtoUtil() {}

}
