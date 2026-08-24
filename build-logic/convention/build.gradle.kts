import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.panov.sevastyan.ndpa.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.panov.sevastyan.ndpa.android.application"
            implementationClass = "com.panov.sevastyan.ndpa.buildlogic.android.AndroidApplicationConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "com.panov.sevastyan.ndpa.kotlin.multiplatform"
            implementationClass = "com.panov.sevastyan.ndpa.buildlogic.kmp.KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "com.panov.sevastyan.ndpa.compose.multiplatform"
            implementationClass = "com.panov.sevastyan.ndpa.buildlogic.kmp.ComposeMultiplatformConventionPlugin"
        }
        register("detekt") {
            id = "com.panov.sevastyan.ndpa.detekt"
            implementationClass = "com.panov.sevastyan.ndpa.buildlogic.quality.DetektConventionPlugin"
        }
    }
}

tasks.validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
}
