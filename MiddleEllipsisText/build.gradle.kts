plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("maven-publish")
  signing
}

android {
  compileSdk = 33

  defaultConfig {
    minSdk = 24
    targetSdk = 32

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFile("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.kotlin.compiler.extension.get()
  }
  testOptions {
    unitTests.isIncludeAndroidResources = true
    managedDevices {
      devices.create<com.android.build.api.dsl.ManagedVirtualDevice>("pixel4Api31") {
        device = "Pixel 4"
        apiLevel = 31
        systemImageSource = "aosp"
      }
    }
  }
}

dependencies {
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.runtime)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material)

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

signing {
  useInMemoryPgpKeys(
    rootProject.extra["signing.keyId"] as String,
    rootProject.extra["signing.key"] as String,
    rootProject.extra["signing.password"] as String,
  )
  sign(publishing.publications)
}

val libName = "middle-ellipsis-text"

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("maven") {
        groupId = "io.github.mataku"
        artifactId = libName
        version = "0.0.2"
        if (project.plugins.hasPlugin("com.android.library")) {
          from(components["release"])
        } else {
          from(components["java"])
        }
        artifact(androidSourcesJar)
        pom {
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
