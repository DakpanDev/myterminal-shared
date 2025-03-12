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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared-Domain"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {

            implementation(libs.koin.annotations)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.moveagency.myterminal.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }
}
