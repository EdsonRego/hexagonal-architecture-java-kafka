package com.edsonrego.hexagonal.application.ports.out;

import com.edsonrego.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);

}
