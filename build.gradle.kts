import io.spring.gradle.dependencymanagement.dsl.DependenciesHandler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  id("findbugs")
  id("io.mverse.project")
  id("io.mverse.multi-module")
  kotlin("jvm")
  id("kotlinx-serialization")
}

allprojects {
  plugins.apply("java")
  group = "io.mverse"
  mverse {
    isDefaultDependencies = false
    dependencies {
      compile(kotlinStdlib())
      compile(kotlinTest())
      compile(kotlinImmutable())
      compile(assertK())
      testCompile(junit())
    }

    bom = "io.mverse:mverse-bom:0.5.13"
    coverageRequirement = 0.39
  }

  tasks.withType(Test::class) {
    systemProperty("user.timezone", "America/New_York")
  }

  tasks.withType<KotlinCompile> {
    val isTestSource = name.endsWith("compileTestKotlin")
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
      suppressWarnings = isTestSource
    }
  }

  repositories {
    maven("https://kotlin.bintray.com/kotlinx")
  }

  dependencyManagement {
    dependencies {
      installKotlinDeps()
      dependency("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.1")
      dependencySet("com.willowtreeapps.assertk:0.11") {
        entry("assertk-jvm")
        entry("assertk-common")
      }
      dependency("com.joestelmach:natty:0.10.1") {
        this.exclude("org.antlr:antlr")
      }
      dependency("io.mverse:hashkode:1.0.1")
    }
  }
}

fun DependenciesHandler.installKotlinDeps() {
  val kotlinCoroutines: String by project
  val kotlin: String by project
  val kotlinSerialization: String by project
  val kotlinIO: String by project
  // None
  dependencySet("org.jetbrains.kotlin:$kotlin") {
    entry("kotlin-stdlib")
    entry("kotlin-runtime")
    entry("kotlin-stdlib-common")
    entry("kotlin-stdlib-jdk7")
    entry("kotlin-stdlib-jdk8")
    entry("kotlin-reflect")
    entry("kotlin-test-annotations-common")
    entry("kotlin-test")
    entry("kotlin-test-junit")
  }

  dependencySet("org.jetbrains.kotlinx:$kotlinCoroutines") {
    entry("kotlinx-coroutines-core")
    entry("kotlinx-coroutines-core-common")
    entry("kotlinx-coroutines-jdk8")
  }

  dependencySet("org.jetbrains.kotlinx:$kotlinIO") {
    entry("kotlinx-io")
    entry("kotlinx-io-jvm")
    entry("kotlinx-coroutines-io")
    entry("kotlinx-coroutines-io-jvm")
  }

  dependencySet("org.jetbrains.kotlinx:$kotlinSerialization") {
    entry("kotlinx-serialization-runtime")
    entry("kotlinx-serialization-runtime-common")
    entry("kotlinx-serialization-runtime-jsonparser")
  }
}

