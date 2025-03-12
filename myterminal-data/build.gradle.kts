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
                implementation(project(":myterminal-domain"))

                implementation(shared.kotlinx.coroutines)
                implementation(shared.kotlinx.datetime)

                implementation(shared.koin.annotations)
                implementation(shared.koin.core)

                implementation(shared.ktor.core)
                implementation(shared.ktor.cio)
                implementation(shared.ktor.client.content.negotiation)
                implementation(shared.ktor.serialization)

                // TODO
                // implementation(shared.room.runtime)
                // implementation(shared.room.kotlin.extensions)
            }
        }
    }
}

android {
    namespace = "com.moveagency.myterminal.data"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    ksp(shared.room.compiler)
}
