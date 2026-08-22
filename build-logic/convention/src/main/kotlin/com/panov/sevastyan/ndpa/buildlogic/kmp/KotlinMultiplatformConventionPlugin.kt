package com.panov.sevastyan.ndpa.buildlogic.kmp

import com.android.build.api.dsl.KotlinMultiplatformAndroidDeviceTestCompilation
import com.android.build.api.dsl.KotlinMultiplatformAndroidHostTestCompilation
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.panov.sevastyan.ndpa.buildlogic.installGitHooksBeforeKotlinCompilation
import com.panov.sevastyan.ndpa.buildlogic.libs
import com.panov.sevastyan.ndpa.buildlogic.versionInt

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.kotlin.multiplatform.library")

        extensions.configure<KotlinMultiplatformExtension> {
            iosArm64()
            iosSimulatorArm64()

            targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
                compileSdk = libs.versionInt("android-compileSdk")
                minSdk = libs.versionInt("android-minSdk")

                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }

                compilations.withType(KotlinMultiplatformAndroidHostTestCompilation::class.java)
                    .configureEach {
                        isIncludeAndroidResources = true
                    }

                compilations.withType(KotlinMultiplatformAndroidDeviceTestCompilation::class.java)
                    .configureEach {
                        instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    }
            }
        }

        installGitHooksBeforeKotlinCompilation()
    }
}
