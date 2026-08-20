plugins {
    id("com.panov.sevastyan.ndpa.compose.multiplatform")
}

kotlin {
    android {
        namespace = "com.panov.sevastyan.ndpa.shared.ui"
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.design)
            api(projects.core.strings)
            api(projects.core.ui)
            api(projects.shared.logic)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
    }
}
