package com.edsonrego.hexagonal.application.core.usecase;

import com.edsonrego.hexagonal.application.core.domain.Customer;
import com.edsonrego.hexagonal.application.ports.out.FindCustomerByIdOutPutPort;

public class FindCustomerByIdUseCase {

    private  final FindCustomerByIdOutPutPort findCustomerByIdOutPutPort;

    public FindCustomerByIdUseCase(FindCustomerByIdOutPutPort findCustomerByIdOutPutPort){
        this.findCustomerByIdOutPutPort = findCustomerByIdOutPutPort;
    }

    public Customer find(String id){
        return findCustomerByIdOutPutPort.find(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }
}
