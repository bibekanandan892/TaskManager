plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}


android {
    namespace = "com.bibek.taskmanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bibek.taskmanager"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirst("META-INF/DEPENDENCIES")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation(kotlin("stdlib"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-functions:20.4.0")
    implementation("androidx.compose.material3:material3-android:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Message Bar Compose
    implementation("com.gitlab.Bibekanandan892:messagebarcomposewithloading:0.0.2")
    //date time picker
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.3.0")
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.3.0")

    val ktor_version = "2.2.4"
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-auth:$ktor_version")
    implementation("com.google.code.gson:gson:2.10")

    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    val camerax_version = "1.4.0-alpha04"
    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-video:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    implementation("androidx.camera:camera-mlkit-vision:${camerax_version}")
    implementation("androidx.camera:camera-extensions:${camerax_version}")



    implementation("io.coil-kt:coil-compose:2.6.0")

    //mockito
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.40.5")

    // Hilt testing dependencies
    testImplementation("com.google.dagger:hilt-android-testing:2.40.1")
    kaptTest("com.google.dagger:hilt-android-compiler:2.40.1")

    testImplementation("androidx.test:runner:1.5.2")
    // Contains the core Credential Manager functionalities including password
    // and passkey support.
    implementation("androidx.credentials:credentials:1.3.0-alpha01")
    // Provides support from Google Play services for Credential Manager,
    // which lets you use the APIs on older devices.
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0-alpha01")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")

    // Google Auth
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // JWT Decoder
    implementation("com.auth0.android:jwtdecode:2.0.2")

    // Gson needed for Proguard rules for JWT Decoder library (For now).
    // Because JWT Decoder doesn't add those Proguard rules by default.
    implementation("com.google.code.gson:gson:2.10.1")

    // One-Tap Compose
    implementation("com.github.stevdza-san:OneTapCompose:1.0.7")

    // Date-Time Picker
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.0.2")

    // CALENDAR
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.0.2")

    // CLOCK
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:1.0.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("com.gitlab.Bibekanandan892:cameracat:0.0.2")

}