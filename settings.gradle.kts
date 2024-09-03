rootProject.name = "DoorbellMp"
include(
    ":composeApp",
    ":navigation",
    ":sharedCore:coreCommon",
    ":sharedCore:coreFirebase",
    ":sharedUi:uiCommon",
    ":sharedUi:uiSplash"
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
