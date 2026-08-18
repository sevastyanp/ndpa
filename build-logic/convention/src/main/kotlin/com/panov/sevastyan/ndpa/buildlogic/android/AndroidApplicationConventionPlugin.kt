package com.panov.sevastyan.ndpa.buildlogic.android

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import com.panov.sevastyan.ndpa.buildlogic.libs
import com.panov.sevastyan.ndpa.buildlogic.versionInt
import com.panov.sevastyan.ndpa.buildlogic.library
import com.panov.sevastyan.ndpa.buildlogic.implementation
import com.panov.sevastyan.ndpa.buildlogic.debugImplementation

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.configure<ApplicationExtension> {
            compileSdk = libs.versionInt("android-compileSdk")

            defaultConfig {
                minSdk = libs.versionInt("android-minSdk")
                targetSdk = libs.versionInt("android-targetSdk")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures {
                compose = true
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }

        dependencies {
            implementation(libs.library("compose-multiplatform-uiToolingPreview"))
            debugImplementation(libs.library("compose-multiplatform-uiTooling"))
        }
    }
}
