import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(shared.plugins.kotlin.multiplatform)
    alias(shared.plugins.kotlin.serialization)
    alias(shared.plugins.android.library)
    alias(shared.plugins.ksp)
    alias(shared.plugins.room)
}

repositories {
    google()
    mavenCentral()
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

                implementation(shared.room.runtime)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(shared.ktor.android)

                implementation(shared.room.runtime.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(shared.ktor.ios)
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    ksp(shared.room.compiler)
}
