import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import ext.androidConfiguration
import ext.composeConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class ApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.android")
      }
      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      extensions.configure<BaseAppModuleExtension> {
        compileSdk = 33
        defaultConfig {
          applicationId = "com.mataku.middleellipsistextsample"
          minSdk = 24
          targetSdk = 32
          versionCode = 1
          versionName = "1.0"
        }
        androidConfiguration()
        kotlinConfiguration()
        composeConfiguration(libs)
      }
    }
  }
}
