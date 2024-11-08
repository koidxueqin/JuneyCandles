plugins {
    alias(libs.plugins.android.application)
}

dependencies {
    implementation(libs.sqlite)  // Correct this to match the catalog entry
}

android {
    namespace = "com.example.juneycandles"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.juneycandles"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.sqlite)
    implementation(libs.navigation.fragment)
    implementation(libs.play.services.analytics.impl)


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)




}
