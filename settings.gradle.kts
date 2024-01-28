pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "podium"

listOf(
    "kommander", "kotlinx-interoperable", "sanity", "cinematic"
).forEach {
    includeBuild("../${it}")
}

// submodules
includeSubs("podium", "common", "dtos", "schemes")
includeSubs("podium", "backend", "app")
includeSubs("podium", "frontend", "browser")
