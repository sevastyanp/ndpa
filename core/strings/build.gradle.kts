plugins {
    id("com.panov.sevastyan.ndpa.compose.multiplatform")
}

kotlin {
    android {
        namespace = "com.panov.sevastyan.ndpa.core.strings"
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.panov.sevastyan.ndpa.core.strings.generated.resources"
}
