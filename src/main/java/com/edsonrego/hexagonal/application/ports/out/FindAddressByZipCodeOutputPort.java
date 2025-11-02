package com.edsonrego.hexagonal.application.ports.out;

import com.edsonrego.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);

}
