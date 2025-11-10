package com.edsonrego.hexagonal.config;

import com.edsonrego.hexagonal.adapters.out.FindAddressByZipCodeAdapater;
import com.edsonrego.hexagonal.adapters.out.UpdateCustomerAdapter;
import com.edsonrego.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import com.edsonrego.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCodeAdapater findAddressByZipCodeAdapater,
            UpdateCustomerAdapter updateCustomerAdapter
    ){
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByZipCodeAdapater, updateCustomerAdapter);
        }
}
