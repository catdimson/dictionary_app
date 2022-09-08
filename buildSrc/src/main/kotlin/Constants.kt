import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.example.dictionaryapp"
    const val compileSdk = 32
    const val minSdk = 26
    const val targetSdk = 32
    val javaVersion = JavaVersion.VERSION_1_8
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

private object DependenciesVersion {
    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val retrofit2KotlinCoroutinesAdapter = "0.9.2"

    // Interceptor
    const val loggingInterceptor = "3.12.1"

    // Coroutines
    const val kotlinxCoroutinesAndroid = "1.6.4"

    // Koin
    const val koinCore = "3.1.2"
    const val koinAndroidCompat = "3.1.2"
    const val koinAndroid = "2.1.6"

    // Room
    const val roomRuntime = "2.4.3"
    const val swiperefreshlayout = "1.1.0"
    const val roomKtx = "2.4.3"

    // Coil
    const val coil = "0.11.0"

    // General
    const val splashScreen = "1.0.0-alpha01"
    const val roomCompiler = "2.4.3"
    const val lifecycleViewmodel = "2.5.1"
    const val lifecycleViewmodelKtx = "2.5.1"
    const val kotlinStdlibJdk7 = "1.7.10"
    const val appcompat = "1.5.0"
    const val coreKtx = "1.8.0"
    const val material = "1.6.1"
    const val constraintlayout = "2.1.4"

    // Tests
    const val junit = "4.13.2"
    const val exTjunit = "1.1.2"
    const val espressoCore = "3.4.0"
    const val coreTesting = "2.1.0"
    const val mockitoCore = "2.25.1"
    const val mockitoInline = "2.13.0"
    const val mockitoKotlin = "1.5.0"
    const val rules = "1.4.0"
    const val runner = "1.4.0"
    const val kotlinxCoroutinesTest = "1.6.4"
    const val kotlinxCoroutinesTestJvm = "1.6.4"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersion.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${DependenciesVersion.converterGson}"
    const val retrofit2KotlinCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependenciesVersion.retrofit2KotlinCoroutinesAdapter}"
}

object Interceptor {
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersion.loggingInterceptor}"
}

object Coroutines {
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersion.kotlinxCoroutinesAndroid}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${DependenciesVersion.koinCore}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${DependenciesVersion.koinAndroidCompat}"
    const val koinAndroid = "io.insert-koin:koin-android:${DependenciesVersion.koinAndroid}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${DependenciesVersion.roomRuntime}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${DependenciesVersion.swiperefreshlayout}"
    const val roomKtx = "androidx.room:room-ktx:${DependenciesVersion.roomKtx}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${DependenciesVersion.coil}"
}

object General {
    const val splashScreen = "androidx.core:core-splashscreen:${DependenciesVersion.splashScreen}"
    const val roomCompiler = "androidx.room:room-compiler:${DependenciesVersion.roomCompiler}"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel:${DependenciesVersion.lifecycleViewmodel}"
    const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersion.lifecycleViewmodelKtx}"
    const val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependenciesVersion.kotlinStdlibJdk7}"
    const val appcompat = "androidx.appcompat:appcompat:${DependenciesVersion.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${DependenciesVersion.coreKtx}"
    const val material = "com.google.android.material:material:${DependenciesVersion.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${DependenciesVersion.constraintlayout}"
}

object Tests {
    const val junit = "junit:junit:${DependenciesVersion.junit}"
    const val exJunit = "androidx.test.ext:junit:${DependenciesVersion.exTjunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${DependenciesVersion.espressoCore}"
    const val coreTesting = "androidx.arch.core:core-testing:${DependenciesVersion.coreTesting}"
    const val mockitoCore = "org.mockito:mockito-core:${DependenciesVersion.mockitoCore}"
    const val mockitoInline = "org.mockito:mockito-inline:${DependenciesVersion.mockitoInline}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${DependenciesVersion.mockitoKotlin}"
    const val rules = "androidx.test:rules:${DependenciesVersion.rules}"
    const val runner = "androidx.test:runner:${DependenciesVersion.runner}"
    const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DependenciesVersion.kotlinxCoroutinesTest}"
    const val kotlinxCoroutinesTestJvm = "org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:${DependenciesVersion.kotlinxCoroutinesTestJvm}"
}