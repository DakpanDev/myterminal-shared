
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(shared.plugins.kotlin.multiplatform)
    alias(shared.plugins.android.library)
    alias(shared.plugins.ksp)
    alias(shared.plugins.skie)
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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            linkerOpts.add("-lsqlite3")
            export(project(":myterminal-domain"))
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                api(project(":myterminal-data"))
            }
        }
    }
}

ksp { arg("KOIN_DEFAULT_MODULE", "false") }

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

dependencies {

    // Project
    commonMainApi(project(":myterminal-domain"))

    // Koin
    commonMainApi(shared.koin.annotations)
    commonMainImplementation(shared.koin.core)

    // Koin compilation per platform
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
