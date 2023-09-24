plugins {
  id("middleellipsistext.android.library")
  id("middleellipsistext.android.compose")
  id("middleellipsistext.android.test")
  id("maven-publish")
  signing
}

android {
  namespace = "com.mataku.middleellipsistext3"

  testOptions {
    managedDevices {
      devices.maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel4Api30").apply {
        device = "Pixel 4"
        apiLevel = 30
        systemImageSource = "aosp-atd"
      }
    }
  }
}

dependencies {
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.runtime)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material3)

  androidTestImplementation(libs.compose.ui.test.junit4)
  debugImplementation(libs.compose.ui.test.manifest)
}

val androidSourcesJar = tasks.register<Jar>("androidSourcesJar") {
  archiveClassifier.set("sources")
  from("android.sourceSets.main.java.srcDirs")
}

artifacts {
  archives(androidSourcesJar)
}
