import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting
        commonMain.dependencies {
            api(project(":myterminal-domain"))
            api(project(":myterminal-data"))

            implementation(libs.koin.annotations)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.moveagency.myterminal.shared"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }
}
