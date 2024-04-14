plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.compose.jb)
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
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  applyDefaultHierarchyTemplate()

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.ui)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.ui)
      }
    }
  }
}

android {
  namespace = "io.github.mataku.middleellipsistext3"
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
}

dependencies {
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.runtime)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material)

  debugImplementation(libs.compose.ui.test.manifest)
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

val libName = "middle-ellipsis-text3"
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
  dependsOn(":MiddleEllipsisText3:signIosX64Publication")
  dependsOn(":MiddleEllipsisText3:signIosArm64Publication")
  dependsOn(":MiddleEllipsisText3:signIosSimulatorArm64Publication")
  dependsOn(":MiddleEllipsisText3:signKotlinMultiplatformPublication")
}

tasks.withType<PublishToMavenRepository> {
  dependsOn(":MiddleEllipsisText3:signIosX64Publication")
  dependsOn(":MiddleEllipsisText3:signIosArm64Publication")
  dependsOn(":MiddleEllipsisText3:signIosSimulatorArm64Publication")
  dependsOn(":MiddleEllipsisText3:signKotlinMultiplatformPublication")
}
