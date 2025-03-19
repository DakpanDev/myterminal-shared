// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(shared.plugins.android.application) apply false
    alias(shared.plugins.kotlin.android) apply false
    alias(shared.plugins.kotlin.multiplatform) apply false
    alias(shared.plugins.android.library) apply false
    alias(shared.plugins.jetbrains.kotlin.jvm) apply false
    alias(shared.plugins.skie) apply false
}