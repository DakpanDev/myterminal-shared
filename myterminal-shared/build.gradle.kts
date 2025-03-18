import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(shared.plugins.kotlin.multiplatform)
    alias(shared.plugins.android.library)
    alias(shared.plugins.ksp)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":myterminal-domain"))
                api(project(":myterminal-data"))

                implementation(shared.koin.annotations)
                implementation(shared.koin.core)
            }
        }
    }
}

android {
    namespace = "com.moveagency.myterminal"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = 26
    }
}
