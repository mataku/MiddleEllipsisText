plugins {
  `kotlin-dsl`
  `maven-publish`
  signing
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
  jvmToolchain(11)
}

dependencies {
  compileOnly(libs.android.gradle.plugin)
  compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
  plugins {
    register("androidLibrary") {
      id = "middleellipsistext.android.library"
      implementationClass = "LibraryConventionPlugin"
    }
    register("androidCompose") {
      id = "middleellipsistext.android.compose"
      implementationClass = "ComposeConventionPlugin"
    }
    register("androidTestConfiguration") {
      id = "middleellipsistext.android.test"
      implementationClass = "TestConventionPlugin"
    }
    register("androidApplication") {
      id = "middleellipsistext.android.application"
      implementationClass = "ApplicationConventionPlugin"
    }
  }
}

