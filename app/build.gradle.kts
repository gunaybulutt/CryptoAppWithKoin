plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("androidx.navigation.safeargs.kotlin")
    //id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.gunay.cryptoappwithkoin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gunay.cryptoappwithkoin"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

val nav_version = "2.7.7"
val lifeCycleExtensionVersion = "1.1.1"
val retrofitVersion = "2.9.0"
val supportVersion = "28.0.0"
val rxJavaVersion = "2.1.1"
val roomVersion = "2.6.1"
val preferencesVersion = "1.2.1"
val glideVersion = "4.12.0"

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Dagger-Hilt
    //implementation("com.google.dagger:hilt-android:2.48")
    //kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Kotlin-Koin
    implementation ("io.insert-koin:koin-android:3.2.0-beta-1")
    implementation ("io.insert-koin:koin-androidx-navigation:3.2.0-beta-1")
    testImplementation ("io.insert-koin:koin-test-junit4:3.2.0-beta-1")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //support-palette
    //implementation ("com.android.support:palette-v7:$supportVersion")
    //implementation ("com.android.support:design:$supportVersion")

    //rxJava
    //implementation("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    //implementation("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")

    //ViewModelProviders
    implementation("android.arch.lifecycle:extensions:$lifeCycleExtensionVersion")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("com.google.android.material:material:1.11.0")

    //glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //SharedPreference
    implementation("androidx.preference:preference:$preferencesVersion")

    //swipeResfleshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
}