plugins {
    id("com.panov.sevastyan.ndpa.compose.multiplatform")
}

kotlin {
    android {
        namespace = "com.panov.sevastyan.ndpa.core.ui"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.design)
            implementation(projects.core.strings)
        }
    }
}
