package com.edsonrego.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.edsonrego.hexagonal")
public class SuffixNamingConventionTest {


    // =============================================
    //  INBOUND (Adapters In)
    // =============================================

    @ArchTest
    public static final ArchRule should_be_suffixed_consumer = classes()
            .that()
            .resideInAnyPackage("..consumer")
            .should()
            .haveSimpleNameEndingWith("Consumer");

    @ArchTest
    public static final ArchRule should_be_suffixed_mapper = classes()
            .that()
            .resideInAnyPackage("..mapper..")
            .should()
            .haveSimpleNameEndingWith("Mapper")
            .orShould()
            .haveSimpleNameEndingWith("MapperImpl");

     @ArchTest
    public static final ArchRule should_be_suffixed_controller = classes()
            .that().resideInAPackage("..adapters.in.controller")
            .should().haveSimpleNameEndingWith("Controller")
            .as("Only main controller classes should end with 'Controller'");

    @ArchTest
    public static final ArchRule should_be_suffixed_request = classes()
            .that().resideInAnyPackage("..adapters.in.controller.request..")
            .should().haveSimpleNameEndingWith("Request")
            .as("Request DTO classes should end with 'Request'");

    @ArchTest
    public static final ArchRule should_be_suffixed_response = classes()
            .that().resideInAnyPackage("..adapters.in.controller.response..")
            .should().haveSimpleNameEndingWith("Response")
            .as("Response DTO classes should end with 'Response'");

    // =============================================
    //  OUTBOUND (Adapters Out)
    // =============================================

    @ArchTest
    public static final ArchRule should_be_suffixed_client = classes()
            .that().resideInAPackage("..adapters.out.client")
            .should().haveSimpleNameEndingWith("Client")
            .as("Feign client interfaces should end with 'Client'");

    @ArchTest
    public static final ArchRule should_be_suffixed_repository = classes()
            .that().resideInAPackage("..adapters.out.repository")
            .should().haveSimpleNameEndingWith("Repository")
            .as("Repository interfaces should end with 'Repository'");

    @ArchTest
    public static final ArchRule should_be_suffixed_entity = classes()
            .that().resideInAnyPackage("..adapters.out.repository.entity..")
            .should().haveSimpleNameEndingWith("Entity")
            .as("Persistence entity classes should end with 'Entity'");

    @ArchTest
    public static final ArchRule should_be_suffixed_adapter = classes()
            .that().resideInAPackage("..adapters.out")
            .should().haveSimpleNameEndingWith("Adapter")
            .as("Output adapter classes should end with 'Adapter'");

    // =============================================
    //  APPLICATION CORE
    // =============================================

    @ArchTest
    public static final ArchRule should_be_suffixed_usecase = classes()
            .that().resideInAnyPackage("..application.core.usecase..")
            .should().haveSimpleNameEndingWith("UseCase")
            .as("Use case classes should end with 'UseCase'");

    @ArchTest
    public static final ArchRule should_be_suffixed_input_port = classes()
            .that().resideInAnyPackage("..application.ports.in..")
            .should().haveSimpleNameEndingWith("InputPort")
            .as("Input port interfaces should end with 'InputPort'");

    // =============================================
    //  CONFIGURATION
    // =============================================

    @ArchTest
    public static final ArchRule should_be_suffixed_config = classes()
            .that().resideInAnyPackage("..config..")
            .should().haveSimpleNameEndingWith("Config")
            .as("Configuration classes should end with 'Config'");
}
