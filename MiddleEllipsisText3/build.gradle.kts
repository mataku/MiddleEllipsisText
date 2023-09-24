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

val libName = "middle-ellipsis-text3"

afterEvaluate {
  publishing {
    publications {
      create<MavenPublication>("maven") {
        groupId = "io.github.mataku"
        artifactId = libName
        version = "0.0.5"
        if (project.plugins.hasPlugin("com.android.library")) {
          from(components["release"])
        } else {
          from(components["java"])
        }
        artifact(androidSourcesJar)
        pom {
          name.set(libName)
          description.set("Jetpack Compose Component with ellipsis in the middle of Material3 text")
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

