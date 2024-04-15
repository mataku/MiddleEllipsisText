import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest

plugins {
  id("middleellipsistext.android.library")
  alias(libs.plugins.roborazzi)
}

kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material)
        implementation(compose.ui)
        implementation(libs.skiko)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(libs.kotlin.test.common)
        implementation(libs.kotlin.test.annotations.common)
        implementation(libs.compose.ui.test)

        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.ui)
      }
    }

    val iosTest by getting {
      dependencies {
        implementation(libs.compose.ui.test)
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material)
        implementation(compose.ui)
      }
    }
    val androidUnitTest by getting {
      dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.kotlin.test.junit)
        implementation(libs.androidx.test.ext.junit)
        implementation(libs.robolectric)
        implementation(libs.roborazzi)
        implementation(libs.compose.ui.test.junit4)
        implementation(libs.compose.ui.test.manifest)
      }
    }
  }

  tasks.getByName<KotlinNativeSimulatorTest>("iosSimulatorArm64Test") {
    device.set("iPhone 13")
  }
  tasks.getByName<KotlinNativeSimulatorTest>("iosX64Test") {
    device.set("iPhone 13")
  }
}

android {
  dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)

    debugImplementation(libs.compose.ui.test.manifest)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.robolectric)
  }
}
