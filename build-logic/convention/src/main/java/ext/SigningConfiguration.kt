package ext

import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.tasks.PublishToMavenLocal
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension


fun Project.signingConfiguration() {
  val project = this
  with(pluginManager) {
    apply("signing")
    apply("maven-publish")
  }
  extensions.configure<SigningExtension>() {
    useInMemoryPgpKeys(
      System.getenv("SIGNING_KEY_ID") ?: rootProject.extra["signing.keyId"] as String,
      System.getenv("SIGNING_KEY") ?: rootProject.extra["signing.key"] as String,
      "",
    )
    extensions.configure<PublishingExtension> {
      sign(this.publications)
    }
  }
  group = rootProject.properties["groupId"] as String
  version = rootProject.properties["version"] as String
  extensions.configure<ExtraPropertiesExtension>() {
    set("signing.password", "")
  }

  afterEvaluate {
    extensions.configure<PublishingExtension>() {
      repositories {
        maven {
          name = "Snapshot"
          val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
          url = snapshotsRepoUrl
          credentials {
            username =
              System.getenv("OSSRH_USERNAME") ?: rootProject.extra["ossrhUsername"] as String
            password =
              System.getenv("OSSRH_PASSWORD") ?: rootProject.extra["ossrhPassword"] as String
          }
        }

        maven {
          name = "Release"
          val releasesRepoUrl =
            uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
          url = releasesRepoUrl
          credentials {
            username =
              System.getenv("OSSRH_USERNAME") ?: rootProject.extra["ossrhUsername"] as String
            password =
              System.getenv("OSSRH_PASSWORD") ?: rootProject.extra["ossrhPassword"] as String
          }
        }
      }
      publications.withType<MavenPublication>() {
        val publication = this
        val gitHubUrl = "https://github.com/mataku/MiddleEllipsisText"
        val javadocJar = tasks.register("${publication.name}JavadocJar", Jar::class.java) {
          archiveClassifier.set("javadoc")
          archiveBaseName.set("${archiveBaseName.get()}-${publication.name}")
        }
        artifact(javadocJar)
        pom {
          artifactId = if (publication.name == "kotlinMultiplatform") {
            project.publishingLibName()
          } else {
            "${project.publishingLibName()}-${publication.name}"
          }
          name.set(project.publishingLibName())
          description.set("A Text Component with ellipsis in the middle of text for Jetpack Compose and Compose Multiplatform.")
          url.set(gitHubUrl)

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
            url.set(gitHubUrl)
          }
        }
      }
    }
  }
  val projectName = project.name
  tasks.withType<PublishToMavenLocal> {
    dependsOn(":$projectName:signIosX64Publication")
    dependsOn(":$projectName:signIosArm64Publication")
    dependsOn(":$projectName:signIosSimulatorArm64Publication")
    dependsOn(":$projectName:signKotlinMultiplatformPublication")
  }

  tasks.withType<PublishToMavenRepository> {
    dependsOn(":$projectName:signIosX64Publication")
    dependsOn(":$projectName:signIosArm64Publication")
    dependsOn(":$projectName:signIosSimulatorArm64Publication")
    dependsOn(":$projectName:signKotlinMultiplatformPublication")
  }
}
