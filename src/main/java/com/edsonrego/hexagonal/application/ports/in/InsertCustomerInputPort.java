package com.edsonrego.hexagonal.application.ports.in;

import com.edsonrego.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);
}
