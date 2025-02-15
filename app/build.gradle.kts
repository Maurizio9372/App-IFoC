plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.github.triplet.play") version "3.8.0"
}

android {
    namespace = "com.example.appifoc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appifoc"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions { jvmTarget = "17" }
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.11" }
}

play {
    serviceAccountCredentials.set(file("app-ifoc-c188dfb47005"))
    defaultToAppBundles.set(true)
    releaseStatus.set(com.github.triplet.gradle.androidpublisher.ReleaseStatus.DRAFT)
    track.set("internal")
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "APP IFoC"
include(":app")

tasks.register<Delete>("clean") { delete(layout.buildDirectory) }
