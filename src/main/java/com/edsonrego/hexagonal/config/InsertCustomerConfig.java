package com.edsonrego.hexagonal.config;

import com.edsonrego.hexagonal.adapters.out.FindAddressByZipCodeAdapater;
import com.edsonrego.hexagonal.adapters.out.InssertCustomerAdapter;
import com.edsonrego.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapater findAddressByZipCodeAdapater,
            InssertCustomerAdapter inssertCustomerAdapter){
        return new InsertCustomerUseCase(findAddressByZipCodeAdapater, inssertCustomerAdapter);
    }
}
