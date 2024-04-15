package ext

import org.gradle.api.Project

fun Project.classNamespace(): String {
  return when (name) {
    "MiddleEllipsisText" -> "io.github.mataku.middleellipsistext"
    "MiddleEllipsisText3" -> "io.github.mataku.middleellipsistext3"
    else -> throw IllegalStateException("Unknown project name: $name")
  }
}

fun Project.publishingLibName(): String {
  return when (name) {
    "MiddleEllipsisText" -> "middle-ellipsis-text"
    "MiddleEllipsisText3" -> "middle-ellipsis-text3"
    else -> throw IllegalStateException("Unknown project name: $name")
  }
}
