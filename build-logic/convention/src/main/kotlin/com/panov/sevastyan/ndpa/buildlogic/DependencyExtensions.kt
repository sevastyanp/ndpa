package com.panov.sevastyan.ndpa.buildlogic

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependency: Any): Dependency? =
    add("implementation", dependency)

internal fun DependencyHandler.debugImplementation(dependency: Any): Dependency? =
    add("debugImplementation", dependency)

internal fun DependencyHandler.androidRuntimeClasspath(dependency: Any): Dependency? =
    add("androidRuntimeClasspath", dependency)

internal fun DependencyHandler.detektPlugins(dependency: Any): Dependency? =
    add("detektPlugins", dependency)
