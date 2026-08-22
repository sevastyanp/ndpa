package com.panov.sevastyan.ndpa.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

private const val INSTALL_GIT_HOOKS_PATH = ":installGitHooks"

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.versionInt(alias: String): Int =
    findVersion(alias).get().requiredVersion.toInt()

internal fun VersionCatalog.library(alias: String): MinimalExternalModuleDependency =
    findLibrary(alias).get().get()

internal fun Project.installGitHooksBeforeKotlinCompilation() {
    tasks.withType(KotlinCompilationTask::class.java).configureEach {
        dependsOn(INSTALL_GIT_HOOKS_PATH)
    }
}
