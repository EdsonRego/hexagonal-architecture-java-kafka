package com.edsonrego.hexagonal.application.ports.in;

import com.edsonrego.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);
}
