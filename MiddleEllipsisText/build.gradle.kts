import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.compose.jb)
  alias(libs.plugins.roborazzi)
  id("maven-publish")
  signing
}

kotlin {
  androidTarget {
    publishLibraryVariants("release")
    compilations.all {
      kotlinOptions {
        jvmTarget = "11"
      }
    }
  }
  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "middle-ellipsis-text"
      isStatic = true
    }
  }

  applyDefaultHierarchyTemplate()

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
  namespace = "io.github.mataku.middleellipsistext"
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

  testOptions {
    unitTests.isIncludeAndroidResources = true
  }
}


ext["signing.password"] = ""

signing {
  useInMemoryPgpKeys(
    rootProject.extra["signing.keyId"] as String,
    rootProject.extra["signing.key"] as String,
    "",
  )
  sign(publishing.publications)
}

val libName = "middle-ellipsis-text"

group = "io.github.mataku"
version = "1.0.1-SNAPSHOT"

afterEvaluate {
  publishing {
    publications.forEach {
      val publication = it as? MavenPublication ?: return@forEach
      with(publication) {
        pom {
          artifactId = if (publication.name == "kotlinMultiplatform") {
            libName
          } else {
            "${libName}-${publication.name}"
          }
          name.set(libName)
          description.set("Jetpack Compose Component with ellipsis in the middle of text")
          url.set("https://github.com/mataku/MiddleEllipsisText")

          licenses {
            license {
              name.set("Apache License 2.0")
              url.set("https://github.com/mataku/MiddleEllipsisText/blob/develop/license/LICENSE.txt")
            }
          }
          developers {
            developer {
              id.set("mataku")
              name.set("mataku")
              url.set("https://github.com/mataku")
            }
          }
          scm {
            connection.set("scm:git:github.com/mataku/MiddleEllipsisText")
            developerConnection.set("scm:git:ssh://github.com/mataku/MiddleEllipsisText")
            url.set("https://github.com/mataku/MiddleEllipsisText")
          }
        }
      }
    }
  }
}

tasks.withType<PublishToMavenLocal> {
  dependsOn(":MiddleEllipsisText:signIosX64Publication")
  dependsOn(":MiddleEllipsisText:signIosArm64Publication")
  dependsOn(":MiddleEllipsisText:signIosSimulatorArm64Publication")
  dependsOn(":MiddleEllipsisText:signKotlinMultiplatformPublication")
}

tasks.withType<PublishToMavenRepository> {
  dependsOn(":MiddleEllipsisText:signIosX64Publication")
  dependsOn(":MiddleEllipsisText:signIosArm64Publication")
  dependsOn(":MiddleEllipsisText:signIosSimulatorArm64Publication")
  dependsOn(":MiddleEllipsisText:signKotlinMultiplatformPublication")
}
