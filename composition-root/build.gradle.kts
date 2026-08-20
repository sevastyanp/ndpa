import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.panov.sevastyan.ndpa.compose.multiplatform")
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "Shared"
            isStatic = true
            binaryOption("bundleId", "com.panov.sevastyan.ndpa.shared")

            export(projects.shared.logic)
        }
    }

    android {
        namespace = "com.panov.sevastyan.ndpa.composition.root"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.shared.ui)
            api(projects.shared.logic)
        }
    }
}
