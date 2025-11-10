package com.edsonrego.hexagonal.config;

import com.edsonrego.hexagonal.adapters.out.FindAddressByZipCodeAdapater;
import com.edsonrego.hexagonal.adapters.out.InsertCustomerAdapter;
import com.edsonrego.hexagonal.adapters.out.SendCpfValidationAdapter;
import com.edsonrego.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapater findAddressByZipCodeAdapater,
            InsertCustomerAdapter insertCustomerAdapter,
            SendCpfValidationAdapter sendCpfValidationAdapter){
        return new InsertCustomerUseCase(findAddressByZipCodeAdapater, insertCustomerAdapter, sendCpfValidationAdapter);
    }
}
