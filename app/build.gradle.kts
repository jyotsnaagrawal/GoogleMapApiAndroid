plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.jyotsna.googlemapsapi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jyotsna.googlemapsapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("com.google.android.gms:play-services-maps:18.0.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
}
