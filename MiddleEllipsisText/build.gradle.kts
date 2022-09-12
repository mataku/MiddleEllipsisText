plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("maven-publish")
}

android {
  compileSdk = 32

  defaultConfig {
    minSdk = 23
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
    kotlinCompilerExtensionVersion = "1.3.0"
  }
}

dependencies {
  implementation("androidx.compose.ui:ui:1.2.1")
  implementation("androidx.compose.runtime:runtime:1.2.1")
  implementation("androidx.compose.foundation:foundation:1.2.1")
  implementation("androidx.compose.material:material:1.2.1")
}

val androidSourcesJar = tasks.register<Jar>("androidSourcesJar") {
  archiveClassifier.set("sources")
  from("android.sourceSets.main.java.srcDirs")
}

artifacts {
  archives(androidSourcesJar)
}

val libName = "middle-ellipsis-text"

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("maven") {
        groupId = "io.github.mataku"
        artifactId = libName
        version = "0.0.1"
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
