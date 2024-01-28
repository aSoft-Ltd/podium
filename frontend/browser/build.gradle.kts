plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(libs.cinematic.live.compose)
        }
    }
}