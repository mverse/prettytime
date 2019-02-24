import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

rootProject.name = "prettytime"

include("prettytime-nlp")
include("prettytime-core")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    maven("https://dl.bintray.com/mverse-io/mverse-public")
  }

  val kotlin:String by settings
  val mversePlugin:String by settings

  val pluginVersionMap = mapOf(
      "kotlinx-serialization" to kotlin,
      "org.jetbrains.kotlin.jvm" to kotlin,
      "org.jetbrains.kotlin.common" to kotlin,
      "io.mverse.project" to mversePlugin,
      "io.mverse.multi-platform" to mversePlugin,
      "io.mverse.multi-module" to mversePlugin)

  resolutionStrategy {
    eachPlugin {

      if (requested.id.id in pluginVersionMap) {
        useVersion(pluginVersionMap[requested.id.id])
      }

      if (requested.id.id == "kotlinx-serialization") {
        useModule("org.jetbrains.kotlin:kotlin-serialization:${target.version}")
      }
    }
  }
}

