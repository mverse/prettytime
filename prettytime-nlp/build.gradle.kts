
plugins {
  kotlin("jvm")
}

mverse {
  dependencies {
    compile("natty")
  }
}

dependencies {
  compile(project(":prettytime-core"))
}
