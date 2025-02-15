plugins {
    id("com.android.application") version "8.8.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("com.github.triplet.play") version "3.5.0" apply false
}

tasks.register<Delete>("clean") { delete(layout.buildDirectory) }

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
