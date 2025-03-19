import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":myterminal-domain"))

                implementation(shared.kotlinx.coroutines)
                implementation(shared.kotlinx.datetime)

                api(shared.koin.annotations)
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

room {
    schemaDirectory("$projectDir/schemas")
}

ksp { arg("KOIN_DEFAULT_MODULE", "false") }

dependencies {

    // Android
    add("kspAndroid", shared.room.compiler)

    // iOS
    add("kspIosSimulatorArm64", shared.room.compiler)
    add("kspIosX64", shared.room.compiler)
    add("kspIosArm64", shared.room.compiler)

    add("kspCommonMainMetadata", shared.koin.compiler)
    add("kspAndroid", shared.koin.compiler)
    add("kspIosX64", shared.koin.compiler)
    add("kspIosArm64", shared.koin.compiler)
    add("kspIosSimulatorArm64", shared.koin.compiler)
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
