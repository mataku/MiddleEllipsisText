import java.io.FileInputStream
import java.util.Properties

plugins {
  id("com.android.application") version "7.4.0" apply false
  id("com.android.library") version "7.4.0" apply false
  id("org.jetbrains.kotlin.android") version "1.7.10" apply false
  id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

ext["ossrhUsername"] = ""
ext["ossrhPassword"] = ""
ext["sonatypeStagingProfileId"] = ""
ext["signing.keyId"] = ""
ext["signing.key"] = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
  val p = Properties()
  FileInputStream(secretPropsFile).use { inputStream -> p.load(inputStream) }
  p.forEach { name, value -> ext[name as String] = value }
} else {
  ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
  ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
  ext["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID")
  ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
  ext["signing.key"] = System.getenv("SIGNING_KEY")
}

nexusPublishing {
  repositories {
    sonatype {
      stagingProfileId.set(rootProject.extra["sonatypeStagingProfileId"] as String)
      username.set(rootProject.extra["ossrhUsername"] as String)
      password.set(rootProject.extra["ossrhPassword"] as String)
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}
