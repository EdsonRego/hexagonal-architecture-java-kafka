package com.edsonrego.hexagonal.adapters.out.client.mapper;

import com.edsonrego.hexagonal.adapters.out.client.response.AddressResponse;
import com.edsonrego.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {
    Address toAddress(AddressResponse addressResponse);
}
