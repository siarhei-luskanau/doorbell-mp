rootProject.name = "DoorbellMp"
include(
    ":composeApp"
)

pluginManagement {
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
