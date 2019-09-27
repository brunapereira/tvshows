package com.thoughtworks.tvshows;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.thoughtworks.tvshows.helper.ArchitectureTest.*;
import static com.tngtech.archunit.base.DescribedPredicate.equalTo;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.dcec.peopleevaluations", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayeredArchitectureTest {
    private static final String CONTROLLER = "..controller..";
    private static final String APPLICATION = "..application..";
    private static final String DOMAIN = "..peopleevaluations.domain..";
    private static final String INFRASTRUCTURE = "..infrastructure..";
    private static final String INFRASTRUCTURE_ADAPTER = "..infrastructure.adapter..";
    private static final String INFRASTRUCTURE_REPOSITORY = "..infrastructure.repository..";

    @ArchTest
    public static final ArchRule viewLayerDoesNotAccessInfrastructureLayer =
            noClasses().that().resideInAPackage(CONTROLLER)
                    .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE);

    @ArchTest
    public static final ArchRule applicationLayerDoesNotAccessInfrastructureLayer =
            classes().that().resideInAPackage(APPLICATION)
                    .and(accessClassesThatResideInPackageButNotInOther(INFRASTRUCTURE, INFRASTRUCTURE_ADAPTER))
                    .should().containNumberOfElements(equalTo(0))
                    .because(THRESHOLD_MESSAGE);

    @ArchTest
    public static final ArchRule domainLayerDoesNotAccessInfrastructureLayer =
            noClasses().that().resideInAPackage(DOMAIN)
                    .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessViewLayer =
            noClasses().that().resideInAPackage(INFRASTRUCTURE)
                    .should().accessClassesThat().resideInAPackage(CONTROLLER);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessApplicationLayer =
            noClasses().that().resideInAPackage(INFRASTRUCTURE)
                    .should().accessClassesThat().resideInAPackage(APPLICATION);

    @ArchTest
    public static final ArchRule infrastructureLayerDoesNotAccessDomainLayer =
            classes().that().resideInAPackage(INFRASTRUCTURE)
                    .and().resideOutsideOfPackage(INFRASTRUCTURE_ADAPTER)
                    .and().resideOutsideOfPackage(INFRASTRUCTURE_REPOSITORY)
                    .and(accessClassesThatResideInPackage(DOMAIN))
                    .should().containNumberOfElements(equalTo(0))
                    .because(THRESHOLD_MESSAGE);

    @ArchTest
    public static final ArchRule infrastructureLayerRepositoryDoesNotAccessDomainLayer =
            classes().that().resideInAPackage(INFRASTRUCTURE_REPOSITORY)
                    .and(accessClassesButEnumsThatResideInPackage(DOMAIN))
                    .should().containNumberOfElements(equalTo(0))
                    .because(THRESHOLD_MESSAGE);
}
