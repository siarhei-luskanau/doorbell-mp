rootProject.name = "DoorbellMp"
include(
    ":composeApp",
    ":navigation",
    ":sharedCore:coreCommon",
    ":sharedUi:sharedSplash"
)

pluginManagement {
    includeBuild("convention-plugin-multiplatform")
    includeBuild("convention-plugin-test-option")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
