plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt) // Apply the kapt plugin
}

android {
    namespace = "com.example.quotesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quotesapp"
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

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler) // Ensure the annotation processor is included
    implementation(libs.kotlinx.coroutines.core) // Add coroutine core dependency
    implementation(libs.kotlinx.coroutines.android) // Add coroutine android dependency
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // Add lifecycle ViewModel KTX dependency
    implementation(libs.androidx.lifecycle.livedata.ktx) // Add lifecycle LiveData KTX dependency
    implementation(libs.androidx.lifecycle.runtime.ktx) // Add lifecycle Runtime KTX dependency
    implementation(libs.retrofit) // Add Retrofit dependency
    implementation(libs.retrofit.converter.gson) // Add Retrofit Gson converter dependency
    implementation(libs.gson) // Add Gson dependency
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
