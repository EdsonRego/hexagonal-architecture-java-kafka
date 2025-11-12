package com.edsonrego.hexagonal.application.core.usecase;

import com.edsonrego.hexagonal.application.core.domain.Customer;
import com.edsonrego.hexagonal.application.core.exceptions.ObjectNotFoundException;
import com.edsonrego.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.edsonrego.hexagonal.application.ports.out.FindCustomerByIdOutputPort;

public class FindCustomerByIdUseCase implements FindCustomerByIdInputPort {

    private  final FindCustomerByIdOutputPort findCustomerByIdOutPutPort;

    public FindCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutPutPort){
        this.findCustomerByIdOutPutPort = findCustomerByIdOutPutPort;
    }

    @Override
    public Customer find(String id){
        return findCustomerByIdOutPutPort.find(id)
                .orElseThrow(()->new ObjectNotFoundException(id));
    }
}
