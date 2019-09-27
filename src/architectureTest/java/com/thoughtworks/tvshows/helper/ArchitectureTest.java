package com.thoughtworks.tvshows.helper;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.Dependency;
import com.tngtech.archunit.core.domain.JavaClass;

public class ArchitectureTest {
    public static final String THRESHOLD_MESSAGE = "if there are fewer elements, change the threshold";

    public static DescribedPredicate<JavaClass> accessClassesButEnumsThatResideInPackage(final String aPackage) {
        return new DescribedPredicate<>("access classes that reside in package " + aPackage) {
            @Override
            public boolean apply(JavaClass input) {
                return input.getDirectDependenciesFromSelf().stream()
                        .map(Dependency::getTargetClass)
                        .filter(this::isNotAccessingEnum)
                        .anyMatch(this::hasAccessFrom);
            }

            private boolean hasAccessFrom(JavaClass targetClass) {
                return targetClass.getName().contains(aPackage.replace("..", "."));
            }

            private boolean isNotAccessingEnum(JavaClass targetClass) {
                return !targetClass.isEnum();
            }
        };
    }

    public static DescribedPredicate<JavaClass> accessClassesThatResideInPackage(final String aPackage) {
        return new DescribedPredicate<>("access classes that reside in package " + aPackage) {
            @Override
            public boolean apply(JavaClass input) {
                return input.getDirectDependenciesFromSelf().stream()
                        .map(Dependency::getTargetClass)
                        .anyMatch(this::hasAccessFrom);
            }

            private boolean hasAccessFrom(JavaClass targetClass) {
                return targetClass.getName().contains(aPackage.replace("..", "."));
            }
        };
    }

    public static DescribedPredicate<JavaClass> accessClassesThatResideInPackageButNotInOther(final String forbiddenPackage, final String allowedPackage) {
        return new DescribedPredicate<>(String.format("access classes that reside in package %s and doesn't reside in package %s", forbiddenPackage, allowedPackage)) {
            @Override
            public boolean apply(JavaClass input) {
                return input.getDirectDependenciesFromSelf().stream()
                        .map(Dependency::getTargetClass)
                        .anyMatch(this::hasAccessFrom);
            }

            private boolean hasAccessFrom(JavaClass targetClass) {
                return targetClass.getName().contains(forbiddenPackage.replace("..", "."))
                        && !targetClass.getName().contains(allowedPackage.replace("..", "."));
            }
        };
    }
}
