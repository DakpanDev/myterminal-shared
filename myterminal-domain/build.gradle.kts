import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(shared.plugins.kotlin.multiplatform)
    alias(shared.plugins.android.library)
    alias(shared.plugins.ksp)
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

            implementation(shared.koin.annotations)
            implementation(shared.koin.core)
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
