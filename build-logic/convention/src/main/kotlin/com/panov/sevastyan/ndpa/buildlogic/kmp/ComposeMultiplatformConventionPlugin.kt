package com.panov.sevastyan.ndpa.buildlogic.kmp

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.panov.sevastyan.ndpa.buildlogic.androidRuntimeClasspath
import com.panov.sevastyan.ndpa.buildlogic.library
import com.panov.sevastyan.ndpa.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.panov.sevastyan.ndpa.kotlin.multiplatform")
        pluginManager.apply("org.jetbrains.compose")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.getByName("commonMain").dependencies {
                implementation(libs.library("compose-multiplatform-runtime"))
                implementation(libs.library("compose-multiplatform-foundation"))
                implementation(libs.library("compose-multiplatform-material3"))
                implementation(libs.library("compose-multiplatform-ui"))
                implementation(libs.library("compose-multiplatform-components-resources"))
                implementation(libs.library("compose-multiplatform-uiToolingPreview"))
            }

            targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                androidResources {
                    enable = true
                }
            }
        }

        dependencies {
            androidRuntimeClasspath(libs.library("compose-multiplatform-uiTooling"))
        }

        extensions.configure<ComposeCompilerGradlePluginExtension> {
            if (providers.gradleProperty("composeReports").isPresent) {
                reportsDestination.set(layout.buildDirectory.dir("compose-reports"))
                metricsDestination.set(layout.buildDirectory.dir("compose-metrics"))
            }
        }
    }
}
