object Plugins {
  object BuildPlugins {
    const val application = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val spotless = "com.diffplug.spotless"
  }

  // classpath plugins
  const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
  const val kotlinGradlePlugin =
    "org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Versions.stdlib}"

  object Versions {
    const val androidGradlePlugin = "4.1.2"
    const val buildTools = "30.0.2"
  }
}
