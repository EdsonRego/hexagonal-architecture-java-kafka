package com.edsonrego.hexagonal.application.ports.in;

import com.edsonrego.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);

}
