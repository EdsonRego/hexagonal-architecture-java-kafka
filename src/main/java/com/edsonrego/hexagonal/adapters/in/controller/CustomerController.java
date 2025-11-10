package com.edsonrego.hexagonal.adapters.in.controller;

import com.edsonrego.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.edsonrego.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.edsonrego.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.edsonrego.hexagonal.application.core.domain.Customer;
import com.edsonrego.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.edsonrego.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.edsonrego.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.edsonrego.hexagonal.application.ports.in.UpdateCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private FindCustomerByIdInputPort findCustomerByIdInputPort;

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest){
        var customer = customerMapper.toCustomer(customerRequest);
        insertCustomerInputPort.insert(customer, customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id?")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id){
        var customer = findCustomerByIdInputPort.find(id);
        var CustomerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(CustomerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final String id, @Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        updateCustomerInputPort.update(customer, customerRequest.getZipCode());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id){
        deleteCustomerByIdInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
