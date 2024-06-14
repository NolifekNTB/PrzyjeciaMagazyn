plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.przyjeciamagazyn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.przyjeciamagazyn"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }

        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }
}

dependencies {
    //tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.6.8")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.6.8")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit:1.9.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")

    //Mockito
    testImplementation("org.mockito:mockito-core:4.0.0")
    implementation("org.mockito:mockito-core:4.0.0")
    testImplementation ("org.mockito:mockito-inline:3.12.4")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    implementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    //Turbine
    testImplementation ("app.cash.turbine:turbine:0.6.1")
    androidTestImplementation ("app.cash.turbine:turbine:0.6.1")

    //Mock
    implementation(libs.mockk.android)

    // Kotlin Coroutines Test
    testImplementation (libs.kotlinx.coroutines.test)

    // AndroidX Test - Core library
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("androidx.test.ext:junit:1.1.5")

    // AndroidX Test - Runner and Rules
    testImplementation ("androidx.test:runner:1.5.2")
    testImplementation ("androidx.test:rules:1.5.0")

    // Room testing
    testImplementation ("androidx.room:room-testing:2.6.1")

    //Core
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.activity.compose)


    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    //Gson
    implementation (libs.gson)

    //ROOM
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling)


    //Navigation
    implementation (libs.androidx.navigation.compose)
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}