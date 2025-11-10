package com.edsonrego.hexagonal.application.ports.out;

import com.edsonrego.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);
}
