import ext.androidConfiguration
import ext.publishingLibName
import ext.signingConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class LibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.multiplatform")
        apply("org.jetbrains.compose")

        extensions.configure<KotlinMultiplatformExtension> {
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
            iosSimulatorArm64(),
          ).forEach {
            it.binaries.framework {
              baseName = target.publishingLibName()
              isStatic = true
            }
          }
          jvm("desktop")

          applyDefaultHierarchyTemplate()
        }
      }
      androidConfiguration()
      signingConfiguration()
    }
  }
}
