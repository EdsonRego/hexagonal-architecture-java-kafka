package com.edsonrego.hexagonal.adapters.out.repository;

import com.edsonrego.hexagonal.adapters.out.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity,String> {
}
