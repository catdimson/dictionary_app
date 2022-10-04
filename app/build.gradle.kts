plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Releases.versionCode
        versionName = Releases.versionName

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
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
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
    // Custom Utils
    implementation(project(":utils"))

    // Retrofit 2
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)
    implementation(Retrofit.retrofit2KotlinCoroutinesAdapter)

    // Interceptor
    implementation(Interceptor.loggingInterceptor)

    // Coroutines
    implementation(Coroutines.kotlinxCoroutinesAndroid)

    // Koin
    implementation(Koin.koinCore)
    implementation(Koin.koinAndroidCompat)
    implementation(Koin.koinAndroid)

    // Room
    implementation(Room.roomRuntime)
    implementation(Room.swiperefreshlayout)
    implementation(Room.roomKtx)

    // Coil
    implementation(Coil.coil)

    // General
    implementation(General.splashScreen)
    kapt(General.roomCompiler)
    implementation(General.lifecycleViewmodel)
    implementation(General.lifecycleViewmodelKtx)
    implementation(General.kotlinStdlibJdk7)
    implementation(General.appcompat)
    implementation(General.coreKtx)
    implementation(General.material)
    implementation(General.constraintlayout)

    testImplementation(Tests.junit)
    androidTestImplementation(Tests.exJunit)
    androidTestImplementation(Tests.espressoCore)
}