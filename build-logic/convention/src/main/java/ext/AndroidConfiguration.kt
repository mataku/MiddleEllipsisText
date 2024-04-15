package ext

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun CommonExtension<*, *, *, *, *>.androidConfiguration() {
  compileSdk = 34
  if (this is BaseAppModuleExtension) {
    defaultConfig {
      minSdk = 24
      targetSdk = 32
    }
  }
}

fun Project.androidConfiguration() {
  val project = this
  extensions.configure<com.android.build.gradle.LibraryExtension>() {
    namespace = project.classNamespace()
    compileSdk = 34

    defaultConfig {
      minSdk = 24

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions.unitTests.isIncludeAndroidResources = true
  }
}
