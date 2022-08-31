plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.dictionaryapp"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs.plus("-Xjvm-default=compatibility")
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    //Koin
    implementation("io.insert-koin:koin-core:3.1.2")
    implementation("io.insert-koin:koin-android-compat:3.1.2")
    implementation("io.insert-koin:koin-android:2.1.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Room
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.room:room-ktx:2.4.3")

    // Coil
    implementation("io.coil-kt:coil:0.11.0")

    // General
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.10")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}