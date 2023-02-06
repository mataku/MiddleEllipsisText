import com.android.build.gradle.LibraryExtension
import ext.testConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class TestConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        extensions.configure<LibraryExtension> {
          testConfiguration()
        }
      }
    }
  }
}
