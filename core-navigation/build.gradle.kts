plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.neurix.core.navigation"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core modules
    implementation(project(":core-design"))


    // Compose BOM
    implementation(platform(libs.compose.bom))

    // Compose dependencies
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.animation)
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("androidx.compose.animation:animation")

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
}
