import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.m2p"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.m2p"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding{
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("com.google.firebase:firebase-messaging-ktx:24.0.0")
    val nav_version = "2.7.5"
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("com.google.android.material:material:1.8.0")// Check for the latest version


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // To use Kotlin Symbol Processing (KSP)
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    kapt("com.github.bumptech.glide:compiler:4.14.2")

    implementation("com.google.android.material:material:1.7.0")
  //  implementation ("com.google.firebase:firebase-messaging:23.2.0") // Check for the latest version
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation ("androidx.work:work-runtime-ktx:2.8.0")


}