plugins {
    id("com.panov.sevastyan.ndpa.android.application")
}

android {
    namespace = "com.panov.sevastyan.ndpa"

    defaultConfig {
        applicationId = "com.panov.sevastyan.ndpa"
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.compositionRoot)

    implementation(libs.androidx.activity.compose)
}
