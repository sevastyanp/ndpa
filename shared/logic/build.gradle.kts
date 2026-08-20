plugins {
    id("com.panov.sevastyan.ndpa.kotlin.multiplatform")
}

kotlin {
    android {
        namespace = "com.panov.sevastyan.ndpa.shared.logic"

        withHostTest {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
