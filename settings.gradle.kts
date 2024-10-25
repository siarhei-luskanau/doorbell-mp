rootProject.name = "DoorbellMp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":composeApp",
    ":navigation",
    ":sharedCore:coreCommon",
    ":sharedCore:coreFirebase",
    ":sharedUi:uiAuth",
    ":sharedUi:uiCommon",
    ":sharedUi:uiPermissions",
    ":sharedUi:uiSplash"
)

pluginManagement {
    includeBuild("convention-plugin-gs-parser")
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
