pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}
dependencyResolutionManagement {
//  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
  versionCatalogs {
    maybeCreate("sampleLibs").apply {
      library("core-ktx", "androidx.core:core-ktx:1.7.0")
      library("appcompat", "androidx.appcompat:appcompat:1.5.0")
      library("ui-tooling", "androidx.compose.ui:ui-tooling:1.2.1")
      library(
        "customview-poolingcontainer",
        "androidx.customview:customview-poolingcontainer:1.0.0"
      )
      library("activity-compose", "androidx.activity:activity-compose:1.5.1")
      library("material", "com.google.android.material:material:1.6.1")
    }
  }
}
rootProject.name = "MiddleEllipsisText"
include(":sample")
include(":MiddleEllipsisText")
include(":MiddleEllipsisText3")
// include(":shared")
