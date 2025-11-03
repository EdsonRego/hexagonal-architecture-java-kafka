package com.edsonrego.hexagonal.adapters.out.repository.mapper;

import com.edsonrego.hexagonal.adapters.out.repository.entity.CustomerEntity;
import com.edsonrego.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);
}
