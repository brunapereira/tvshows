package com.tvshows;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.thoughtworks.tvshows", importOptions = ImportOption.DoNotIncludeTests.class)
public class ClassesTest {
    @ArchTest
    public static final ArchRule controllersMustResideInViewPackage =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule servicesMustResideInApplicationPackage =
            classes().that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("..application..");

    @ArchTest
    public static final ArchRule adaptersMustResideInAdapterPackage =
            classes().that().haveSimpleNameEndingWith("Adapter")
                    .should().resideInAPackage("..infrastructure.adapter..");

    @ArchTest
    public static final ArchRule convertersMustNotDependOfRepositoriesOrGateways =
            noClasses().that().resideInAPackage("..infrastructure.adapter..").and().haveSimpleNameEndingWith("Converter")
                    .should().dependOnClassesThat().haveSimpleNameEndingWith("Gateway")
                    .orShould().dependOnClassesThat().areAssignableTo(JpaRepository.class);

    @ArchTest
    public static final ArchRule gatewayMustResideInGatewayPackage =
            classes().that().haveSimpleNameEndingWith("Gateway")
                    .should().resideInAPackage("..infrastructure.gateway..");

    @ArchTest
    public static final ArchRule entitiesMustResideInARepositoryPackage =
            classes().that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage("..infrastructure.repository.jpa..");

    @ArchTest
    public static final ArchRule repositoriesMustResideInARepositoryPackage =
            classes().that().areAssignableTo(JpaRepository.class)
                    .should().resideInAPackage("..infrastructure.repository.jpa..");

    @ArchTest
    public static final ArchRule convertersMustResideInAConverterPackage =
            classes().that().resideInAPackage("..infrastructure.adapter..")
                    .and().haveSimpleNameEndingWith("Converter")
                    .should().resideInAPackage("..converter..");

    @ArchTest
    public static final ArchRule classesShouldNotBeSuffixedWithDto =
            noClasses()
                    .should().haveSimpleNameEndingWith("Dto")
                    .orShould().haveSimpleNameEndingWith("DTO");
}
