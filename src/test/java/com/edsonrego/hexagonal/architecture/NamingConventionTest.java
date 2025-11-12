package com.edsonrego.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.edsonrego.hexagonal")
public class NamingConventionTest {

    @ArchTest
    public static final ArchRule consumer_reside_only_consumer_package = classes()
            .that()
            .haveNameMatching(".*Consumer")
            .should()
            .resideInAPackage("..adapters.in.consumer")
            .as("Consumer classes should reside inside consumer package in adapters.in.consumer");

    @ArchTest
    public static final ArchRule mapper_reside_only_mmapper_package = classes()
            .that()
            .haveNameMatching(".*Mapper")
            .should()
            .resideInAnyPackage(
                    "..adapters.in.consumer.mapper",
                    "..adapters.in.controller.mapper",
                    "..adapters.out.client.mapper",
                    "..adapters.out.repository.mapper"
            )
            .as("Mapper classes should reside inside mapper package in controller, client, consumer or repository packages");

    @ArchTest
    public static final ArchRule message_reside_only_message_package = classes()
            .that()
            .haveNameMatching(".*Message")
            .should()
            .resideInAPackage("..adapters.in.consumer.message..")
            .as("Message classes should reside inside adapters.in.consumer.message");

    @ArchTest
    public static final ArchRule controller_reside_only_controller_package = classes()
            .that()
            .haveNameMatching(".*Controller")
            .should()
            .resideInAPackage("..adapters.in.controller..")
            .as("Controller classes should reside inside adapters.in.controller");

    @ArchTest
    public static final ArchRule request_reside_only_request_package = classes()
            .that()
            .haveNameMatching(".*Request")
            .should()
            .resideInAPackage("..adapters.in.controller.request..")
            .as("Request classes should reside inside adapters.in.controller.request");

    @ArchTest
    public static final ArchRule controller_response_reside_only_controller_response_package = classes()
            .that().haveSimpleNameEndingWith("Response")
            .and().resideInAPackage("..adapters.in.controller..")
            .should().resideInAPackage("..adapters.in.controller.response..")
            .as("Controller Response classes must live in adapters.in.controller.response");

    @ArchTest
    public static final ArchRule client_response_reside_only_client_response_package = classes()
            .that().haveSimpleNameEndingWith("Response")
            .and().resideInAPackage("..adapters.out.client..")
            .should().resideInAPackage("..adapters.out.client.response..")
            .as("Client Response classes must live in adapters.out.client.response");


    @ArchTest
    public static final ArchRule adapter_reside_only_adapter_package = classes()
            .that()
            .haveNameMatching(".*Adapter")
            .should()
            .resideInAPackage("..adapters.out..")
            .as("Adapter classes should reside inside adapters.out");

    @ArchTest
    public static final ArchRule client_reside_only_client_package = classes()
            .that()
            .haveNameMatching(".*Client")
            .should()
            .resideInAPackage("..adapters.out.client..")
            .as("Client classes should reside inside adapters.out.client");

    @ArchTest
    public static final ArchRule repository_reside_only_repository_package = classes()
            .that()
            .haveNameMatching(".*Repository")
            .should()
            .resideInAPackage("..adapters.out.repository..")
            .as("Repository classes should reside inside adapters.out.repository");

    @ArchTest
    public static final ArchRule entity_reside_only_entity_package = classes()
            .that()
            .haveNameMatching(".*Entity")
            .should()
            .resideInAPackage("..adapters.out.repository.entity..")
            .as("Entity classes should reside inside adapters.out.repository.entity");

    @ArchTest
    public static final ArchRule usecase_reside_only_usecase_package = classes()
            .that()
            .haveNameMatching(".*UseCase")
            .should()
            .resideInAPackage("..application.core.usecase..")
            .as("UseCase classes should reside inside application.core.usecase");

    @ArchTest
    public static final ArchRule input_reside_only_input_package = classes()
            .that()
            .haveNameMatching(".*InputPort")
            .should()
            .resideInAPackage("..application.ports.in..")
            .as("InputPort interfaces should reside inside application.ports.in");

    @ArchTest
    public static final ArchRule output_reside_only_output_package = classes()
            .that()
            .haveNameMatching(".*OutputPort")
            .should()
            .resideInAPackage("..application.ports.out..")
            .as("OutputPort interfaces should reside inside application.ports.out");

    @ArchTest
    public static final ArchRule config_reside_only_config_package = classes()
            .that()
            .haveNameMatching(".*Config")
            .should()
            .resideInAPackage("..config..")
            .as("Config classes should reside inside config package");


}
