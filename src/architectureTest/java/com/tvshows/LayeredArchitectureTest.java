package com.tvshows;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tvshows.helper.ArchitectureTest.*;
import static com.tngtech.archunit.base.DescribedPredicate.equalTo;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.tvshows", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayeredArchitectureTest {
    private static final String CONTROLLER = "..controller..";
    private static final String APPLICATION = "..application..";
    private static final String DOMAIN = "..tvshows.domain..";
    private static final String INFRASTRUCTURE = "..infrastructure..";
    private static final String INFRASTRUCTURE_ADAPTER = "..infrastructure.adapter..";
    private static final String INFRASTRUCTURE_REPOSITORY = "..infrastructure.jpa..";
    private static final String INFRASTRUCTURE_GATEWAY = "..infrastructure.gateway..";

    @ArchTest
    public static final ArchRule viewLayerDoesNotAccessInfrastructureLayer =
            noClasses().that().resideInAPackage(CONTROLLER)
                    .and(resideOutsideProxyPackages())
                    .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE);

    @ArchTest
    public static final ArchRule applicationLayerDoesNotAccessInfrastructureLayer =
            noClasses().that().resideInAPackage(APPLICATION)
                    .and(resideOutsideProxyPackages())
                    .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE_REPOSITORY)
                    .andShould().accessClassesThat().resideInAPackage(INFRASTRUCTURE_GATEWAY);

    @ArchTest
    public static final ArchRule domainLayerDoesNotAccessInfrastructureLayer =
            noClasses().that().resideInAPackage(DOMAIN)
                    .and(resideOutsideProxyPackages())
                    .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE)
                    .andShould().accessClassesThat().resideInAPackage(INFRASTRUCTURE_GATEWAY);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessViewLayer =
            noClasses().that().resideInAPackage(INFRASTRUCTURE)
                    .and(resideOutsideProxyPackages())
                    .should().accessClassesThat().resideInAPackage(CONTROLLER);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessApplicationLayer =
            noClasses().that().resideInAPackage(INFRASTRUCTURE)
                    .and(resideOutsideProxyPackages())
                    .should().accessClassesThat().resideInAPackage(APPLICATION);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessDomainLayer =
            noClasses().that().resideInAPackage(INFRASTRUCTURE)
                    .and(resideOutsideProxyPackages())
                    .and().resideOutsideOfPackage(INFRASTRUCTURE_ADAPTER)
                    .and().resideOutsideOfPackage(INFRASTRUCTURE_REPOSITORY)
                    .should().accessClassesThat().resideInAPackage(DOMAIN);

    @ArchTest
    public static final ArchRule infrastructureLayerRepositoryDoesNotAccessDomainLayer =
            classes().that().resideInAPackage(INFRASTRUCTURE_REPOSITORY)
                    .and(accessClassesButEnumsThatResideInPackage(DOMAIN))
                    .should().containNumberOfElements(equalTo(0));

    private static DescribedPredicate<JavaClass> resideOutsideProxyPackages() {
        return resideOutsideOfPackages("..search..");
    }
}
