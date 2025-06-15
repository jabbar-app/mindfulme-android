plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("org.jetbrains.kotlin.kapt")
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "id.jabbar.mindfulme"
  compileSdk = 34

  defaultConfig {
    applicationId = "id.jabbar.mindfulme"
    minSdk = 28
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  // The Compose BOM (Bill of Materials) manages the versions of all Compose libraries.
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)

  // Navigation
  implementation(libs.androidx.navigation.compose)

  // ViewModel
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Room Database
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  // 'kapt' is the annotation processor for Room.
  //noinspection KaptUsageInsteadOfKsp
  kapt(libs.androidx.room.compiler)

  // Retrofit for API calls
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.logging.interceptor)

  // Coroutines
  implementation(libs.kotlinx.coroutines.android)

  // Material Icons Extended - The version is now managed by the Compose BOM.
  implementation(libs.androidx.material.icons.extended)

  // WorkManager for reminders
  implementation(libs.androidx.work.runtime.ktx)

  // Date picker
  implementation(libs.datetime)

  // Test dependencies
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}
